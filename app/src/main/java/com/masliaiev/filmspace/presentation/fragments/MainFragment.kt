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
import androidx.recyclerview.widget.RecyclerView
import com.masliaiev.filmspace.databinding.FragmentMainBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.presentation.FilmSpaceApp
import com.masliaiev.filmspace.presentation.activites.DetailActivity
import com.masliaiev.filmspace.presentation.adapters.MovieAdapter
import com.masliaiev.filmspace.presentation.adapters.RecyclerViewPagerAdapter
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

    private val recyclerViewPopularity by lazy {
        RecyclerView(requireActivity())
    }

    private val recyclerViewTopRated by lazy {
        RecyclerView(requireActivity())
    }


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    override fun onAttach(context: Context) {
        Log.d("MainFragment", "onAttach")
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainFragment", "onCreate")
        super.onCreate(savedInstanceState)

        recyclerViewPopularity.layoutManager = GridLayoutManager(context, 2)
        recyclerViewPopularity.adapter = adapterPopularity

        recyclerViewTopRated.layoutManager = GridLayoutManager(context, 2)
        recyclerViewTopRated.adapter = adapterTopRated
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
//        binding.rvPopularityMovies.layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)
//        binding.rvPopularityMovies.adapter = adapterPopularity



        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]

        viewModel.moviesList.observe(viewLifecycleOwner) {

            adapterPopularity.submitData(viewLifecycleOwner.lifecycle, it)

            adapterTopRated.submitData(viewLifecycleOwner.lifecycle, it)

        }



        val rvList = listOf(recyclerViewPopularity, recyclerViewTopRated)

        val viewPager = binding.viewPager
        val viewPagerAdapter = RecyclerViewPagerAdapter(rvList, requireContext())
        val tabLayout = binding.tabLayout
        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = viewPagerAdapter

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

    }

    override fun onDestroyView() {
        Log.d("MainFragment", "onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val COLUMN_COUNT = 2
        private const val SORT_BY_POPULARITY = "popularity.desc"
        private const val SORT_BY_TOP_RATED = "vote_average.desc"
    }


}