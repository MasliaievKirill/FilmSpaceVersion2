package com.masliaiev.filmspace.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.masliaiev.filmspace.databinding.FragmentSearchBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.presentation.FilmSpaceApp
import com.masliaiev.filmspace.presentation.activites.DetailActivity
import com.masliaiev.filmspace.presentation.adapters.SearchedMovieAdapter
import com.masliaiev.filmspace.presentation.adapters.SearchedMoviePagingAdapter
import com.masliaiev.filmspace.presentation.view_models.SearchFragmentViewModel
import com.masliaiev.filmspace.presentation.view_models.ViewModelFactory
import javax.inject.Inject


class SearchFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as FilmSpaceApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SearchFragmentViewModel

    private val adapter by lazy {
        SearchedMovieAdapter()
    }

    private val adapterPaging by lazy {
        SearchedMoviePagingAdapter()
    }

    private var _binding: FragmentSearchBinding? = null
    val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchFragmentViewModel::class.java]
        binding.rvSearchedMoviesFromDb.adapter = adapter
        binding.rvSearchedMovies.adapter = adapterPaging
        binding.rvSearchedMoviesFromDb.layoutManager =
            GridLayoutManager(requireContext(), COLUMN_COUNT)
        binding.rvSearchedMovies.layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)
        viewModel.searchedMoviesListFromDb.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.searchList?.observe(viewLifecycleOwner){
            adapterPaging.submitData(viewLifecycleOwner.lifecycle, it)
        }
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.etSearch.text.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.searchList?.removeObservers(viewLifecycleOwner)
                    viewModel.query = query
                    viewModel.searchMovies(query)
                    viewModel.searchList?.observe(viewLifecycleOwner) {
                        adapterPaging.submitData(viewLifecycleOwner.lifecycle, it)
                    }
                    adapterPaging.refresh()
                    binding.rvSearchedMoviesFromDb.visibility = View.INVISIBLE
                    binding.rvSearchedMovies.visibility = View.VISIBLE
                    binding.tvRecentlySearched.visibility = View.INVISIBLE
                } else {
                    Toast.makeText(requireActivity(), "Need text to query", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            false
        }
        binding.ivDeleteIcon.setOnClickListener {
            binding.etSearch.text.clear()
        }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 0) {
                    binding.ivDeleteIcon.visibility = View.INVISIBLE
                    binding.rvSearchedMoviesFromDb.visibility = View.VISIBLE
                    binding.rvSearchedMovies.visibility = View.INVISIBLE
                    binding.tvRecentlySearched.visibility = View.VISIBLE
                } else {
                    binding.ivDeleteIcon.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        adapter.onSearchedMovieFromDbClickListener =
            object : SearchedMovieAdapter.OnSearchedMovieFromDbClickListener {
                override fun onSearchedMovieClick(movie: Movie) {
                    Toast.makeText(requireActivity(), "Loading...", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(DetailActivity.newIntent(requireActivity(), movie))
                }
            }
        adapterPaging.onSearchedMovieClickListener =
            object : SearchedMoviePagingAdapter.OnSearchedMovieClickListener {
                override fun onMovieClick(movie: Movie) {
                    viewModel.addSearchedMovieInDb(movie)
                    Toast.makeText(requireActivity(), "Loading...", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(DetailActivity.newIntent(requireActivity(), movie))
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (binding.etSearch.text.toString() == viewModel.query && binding.etSearch.text.isNotEmpty()) {
            binding.rvSearchedMovies.visibility = View.VISIBLE
            binding.rvSearchedMoviesFromDb.visibility = View.INVISIBLE
            binding.tvRecentlySearched.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }

        private const val COLUMN_COUNT = 2

    }
}