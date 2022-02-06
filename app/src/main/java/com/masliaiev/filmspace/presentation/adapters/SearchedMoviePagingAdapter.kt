package com.masliaiev.filmspace.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.MovieItemBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.squareup.picasso.Picasso

class SearchedMoviePagingAdapter :
    PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {


    var onSearchedMovieClickListener: OnSearchedMovieClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            Picasso.get().load(movie.posterPath).placeholder(R.drawable.placeholder_large)
                .into(holder.binding.ivSmallPoster)
            holder.binding.tvMovieTitIe.text = movie.title
            holder.binding.root.setOnClickListener {
                onSearchedMovieClickListener?.onMovieClick(movie)
            }
        }
    }

    interface OnSearchedMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

}