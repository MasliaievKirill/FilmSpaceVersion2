package com.masliaiev.filmspace.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masliaiev.filmspace.databinding.FragmentMainBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.presentation.FilmSpaceApp
import com.masliaiev.filmspace.presentation.activites.DetailActivity
import com.masliaiev.filmspace.presentation.adapters.MoviePagerAdapter
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
        MoviePagerAdapter()
    }

    private val adapterTopRated by lazy {
        MoviePagerAdapter()
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
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerViewPopularity.layoutManager = GridLayoutManager(context, COLUMN_COUNT)
        recyclerViewPopularity.adapter = adapterPopularity

        recyclerViewTopRated.layoutManager = GridLayoutManager(context, COLUMN_COUNT)
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

        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]

        viewModel.popularityMoviesList.observe(viewLifecycleOwner) {
            adapterPopularity.submitData(viewLifecycleOwner.lifecycle, it)
        }

        viewModel.topRatedMoviesList.observe(viewLifecycleOwner) {
            adapterTopRated.submitData(viewLifecycleOwner.lifecycle, it)
        }

        val rvList = listOf(recyclerViewPopularity, recyclerViewTopRated)

        val viewPager = binding.viewPager
        val viewPagerAdapter = RecyclerViewPagerAdapter(rvList, requireContext())
        val tabLayout = binding.tabLayout
        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = viewPagerAdapter

        adapterPopularity.onMovieClickListener = object : MoviePagerAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                Toast.makeText(requireActivity(), "Loading...", Toast.LENGTH_SHORT).show()
                startActivity(DetailActivity.newIntent(requireActivity(), movie))
            }
        }

        adapterTopRated.onMovieClickListener = object : MoviePagerAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                Toast.makeText(requireActivity(), "Loading...", Toast.LENGTH_SHORT).show()
                startActivity(DetailActivity.newIntent(requireActivity(), movie))
            }
        }

        adapterPopularity.addLoadStateListener {
            val refreshState = it.refresh
            recyclerViewPopularity.isVisible = refreshState !is LoadState.Loading
            binding.progressBar.isVisible = refreshState is LoadState.Loading
            checkLoadStateError(refreshState)


        }

        adapterTopRated.addLoadStateListener {
            val refreshState = it.refresh
            recyclerViewTopRated.isVisible = refreshState != LoadState.Loading
            binding.progressBar.isVisible = refreshState == LoadState.Loading
            checkLoadStateError(refreshState)

        }

        binding.buttonRetry.setOnClickListener {
            adapterTopRated.refresh()
            adapterPopularity.refresh()
        }

        binding.buttonToFavourite.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToFavouriteFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun checkLoadStateError(loadState: LoadState) {
        binding.tabLayout.isVisible = loadState !is LoadState.Error
        binding.viewPager.isVisible = loadState !is LoadState.Error
        binding.ivWarning.isVisible = loadState is LoadState.Error
        binding.tvWarning.isVisible = loadState is LoadState.Error
        binding.buttonToFavourite.isVisible = loadState is LoadState.Error
        binding.buttonRetry.isVisible = loadState is LoadState.Error
    }

    companion object {
        private const val COLUMN_COUNT = 2
    }


}