package com.masliaiev.filmspace.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.masliaiev.filmspace.databinding.FragmentMainBinding
import com.masliaiev.filmspace.presentation.activites.DetailActivity
import com.masliaiev.filmspace.presentation.adapters.MovieAdapter
import com.masliaiev.filmspace.presentation.view_models.MainFragmentViewModel

class MainFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainFragmentViewModel::class.java]
    }

    private val adapter by lazy {
        MovieAdapter()
    }

//    private var page = 1


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        loadMovies(true, page)
//        page++
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.adapter = adapter
        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)
//        viewModel.getMovies.observe(viewLifecycleOwner) {
//            adapter.movieList = it
//        }
        viewModel.results.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)

        }
        adapter.addLoadStateListener { state:CombinedLoadStates ->
            binding.rvMovies.isVisible = state.refresh != LoadState.Loading
            binding.progressBarLoadingMovies.isVisible = state.refresh == LoadState.Loading
            binding.imageViewWarning.isVisible = state.refresh is LoadState.Error
        }




//        viewModel.errorLoadMovies.observe(viewLifecycleOwner) {
//            if (!it) {
//                Toast.makeText(requireActivity(), "Problem with connection", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }


        adapter.onMovieClickListener = object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movieId: Int) {
                Toast.makeText(requireActivity(), movieId.toString(), Toast.LENGTH_SHORT).show()
                startActivity(DetailActivity.newIntent(requireActivity(), movieId))
            }
        }
//        adapter.onReachEndListener = object : MovieAdapter.OnReachEndListener {
//            override fun onReachEnd() {
//                Toast.makeText(requireActivity(),"End", Toast.LENGTH_SHORT).show()
//                loadMovies(true, page)
//                page++
//            }
//        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun loadMovies (popularity: Boolean, page: Int){
//        viewModel.loadMovies(true, page)
//    }


}