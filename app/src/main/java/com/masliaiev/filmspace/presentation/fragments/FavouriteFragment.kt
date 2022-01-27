package com.masliaiev.filmspace.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.masliaiev.filmspace.databinding.FragmentFavouriteBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.presentation.activites.DetailActivity
import com.masliaiev.filmspace.presentation.adapters.FavouriteMovieAdapter
import com.masliaiev.filmspace.presentation.view_models.FavouriteFragmentViewModel


class FavouriteFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[FavouriteFragmentViewModel::class.java]
    }

    private val adapter by lazy {
        FavouriteMovieAdapter()
    }

    private var _binding: FragmentFavouriteBinding? = null
    private val binding: FragmentFavouriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavouriteBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavouriteMovies.adapter = adapter
        binding.rvFavouriteMovies.layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)
        viewModel.getFavouritesMovies.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.tvFavouriteWarning.visibility = View.VISIBLE
                binding.rvFavouriteMovies.visibility = View.INVISIBLE
            } else {
                binding.tvFavouriteWarning.visibility = View.INVISIBLE
                binding.rvFavouriteMovies.visibility = View.VISIBLE
                adapter.submitList(it)
            }
        }

        adapter.onFavouriteMovieClickListener =
            object : FavouriteMovieAdapter.OnFavouriteMovieClickListener {
                override fun onFavouriteMovieClick(movie: Movie) {
                    Toast.makeText(requireActivity(), movie.id.toString(), Toast.LENGTH_SHORT)
                        .show()
                    startActivity(DetailActivity.newIntent(requireActivity(), movie))
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): FavouriteFragment {
            return FavouriteFragment()
        }

        private const val COLUMN_COUNT = 2
    }

}