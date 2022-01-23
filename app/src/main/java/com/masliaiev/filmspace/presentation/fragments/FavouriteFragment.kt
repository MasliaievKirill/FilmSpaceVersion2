package com.masliaiev.filmspace.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.FragmentFavouriteBinding
import com.masliaiev.filmspace.presentation.activites.DetailActivity
import com.masliaiev.filmspace.presentation.adapters.MovieAdapter
import com.masliaiev.filmspace.presentation.view_models.FavouriteFragmentViewModel
import java.lang.RuntimeException


class FavouriteFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[FavouriteFragmentViewModel::class.java]
    }

    private val adapter by lazy {
        MovieAdapter()
    }

    private var _binding: FragmentFavouriteBinding? = null
    private val binding: FragmentFavouriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavouriteBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavouriteMovies.adapter = adapter
        binding.rvFavouriteMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.getFavouritesMovies.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.tvFavouriteWarning.visibility = View.VISIBLE
            } else {
                binding.tvFavouriteWarning.visibility = View.INVISIBLE
                adapter.submitList(it)
            }
        }
        adapter.onMovieClickListener = object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movieId: Int) {
                Toast.makeText(requireActivity(), movieId.toString(), Toast.LENGTH_SHORT).show()
                startActivity(DetailActivity.newIntent(requireActivity(), movieId))
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}