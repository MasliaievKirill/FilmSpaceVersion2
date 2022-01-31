package com.masliaiev.filmspace.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.masliaiev.filmspace.databinding.FragmentMainBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.presentation.FilmSpaceApp
import com.masliaiev.filmspace.presentation.activites.DetailActivity
import com.masliaiev.filmspace.presentation.adapters.MovieAdapter
import com.masliaiev.filmspace.presentation.view_models.MainFragmentViewModel
import com.masliaiev.filmspace.presentation.view_models.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as FilmSpaceApp).component
    }

    private lateinit var viewModel: MainFragmentViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapterPopularity by lazy {
        MovieAdapter()
    }

    private val adapterTopRated by lazy {
        MovieAdapter()
    }

    private var page = 1

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
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
        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
        if (savedInstanceState == null) {
            viewModel.loadMovies(SORT_BY_POPULARITY, page.toString())
            page++
        }
        binding.rvPopularityMovies.adapter = adapterPopularity
        binding.rvPopularityMovies.layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)

//        binding.rvTopRatedMovies.adapter = adapterTopRated
//        binding.rvTopRatedMovies.layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)

        viewModel.moviesList.observe(viewLifecycleOwner) {
            adapterPopularity.submitList(it)
        }

        adapterPopularity.onReachEndListener = object : MovieAdapter.OnReachEndListener {
            override fun onReachEnd() {
                viewModel.loadMovies(SORT_BY_POPULARITY, page.toString())
                page++
            }
        }

//        viewModel.moviesListTpoRated.observe(viewLifecycleOwner){
//            adapterTopRated.submitData(viewLifecycleOwner.lifecycle, it)
//        }


//        adapterPopularity.addLoadStateListener { state: CombinedLoadStates ->
//            binding.rvPopularityMovies.isVisible = state.refresh != LoadState.Loading
//            binding.progressBarLoadingMovies.isVisible = state.refresh == LoadState.Loading
//            binding.imageViewWarning.isVisible = state.refresh is LoadState.Error
//        }
//
//        adapterTopRated.addLoadStateListener { state: CombinedLoadStates ->
//            binding.progressBarLoadingMovies.isVisible = state.refresh == LoadState.Loading
//        }

        adapterPopularity.onMovieClickListener = object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                Toast.makeText(requireActivity(), movie.id.toString(), Toast.LENGTH_SHORT).show()
                startActivity(DetailActivity.newIntent(requireActivity(), movie))
            }
        }

        adapterTopRated.onMovieClickListener = object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                Toast.makeText(requireActivity(), movie.id.toString(), Toast.LENGTH_SHORT).show()
                startActivity(DetailActivity.newIntent(requireActivity(), movie))
            }
        }


//        binding.buttonPopularity.setOnClickListener {
//            binding.rvPopularityMovies.visibility = View.VISIBLE
//            binding.rvTopRatedMovies.visibility = View.INVISIBLE
//        }
//        binding.buttonTopRated.setOnClickListener {
//            binding.rvPopularityMovies.visibility = View.INVISIBLE
//            binding.rvTopRatedMovies.visibility = View.VISIBLE
//        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainFragment", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainFragment", "onResume")
//        viewModel.favouriteMoviesList.observe(viewLifecycleOwner){
//            adapterPopularity.favouriteMoviesList = it
//            adapterTopRated.favouriteMoviesList = it
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val COLUMN_COUNT = 2
        private const val SORT_BY_POPULARITY = "popularity.desc"
        private const val SORT_BY_TOP_RATED = "vote_average.desc"
    }


}