package com.masliaiev.filmspace.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    private var page = 1


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

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
        binding.rvMovies.recycledViewPool.setMaxRecycledViews(0, MovieAdapter.MAX_POOL_SIZE)

        viewModel.loadMovies(true, page++)

        viewModel.errorLoadMovies.observe(viewLifecycleOwner) {
            if (!it) {
                Toast.makeText(requireActivity(), "Problem with connection", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.getMovies.observe(viewLifecycleOwner) {

            adapter.submitList(it)
        }
        adapter.onMovieClickListener = object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movieId: Int) {
                Toast.makeText(requireActivity(), movieId.toString(), Toast.LENGTH_SHORT).show()
                startActivity(DetailActivity.newIntent(requireActivity(), movieId))
            }
        }
        adapter.onReachEndListener = object : MovieAdapter.OnReachEndListener {
            override fun onReachEnd() {
                viewModel.loadMovies(true, page)
                page++
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}