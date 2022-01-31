package com.masliaiev.filmspace.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.MovieItemBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.squareup.picasso.Picasso

class FavouriteMovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    var onFavouriteMovieClickListener: OnFavouriteMovieClickListener? = null

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
        Picasso.get().load(movie?.posterPath).placeholder(R.drawable.placeholder_large)
            .into(holder.binding.ivSmallPoster)
        holder.binding.root.setOnClickListener {
            onFavouriteMovieClickListener?.onFavouriteMovieClick(movie!!)
        }
    }

    interface OnFavouriteMovieClickListener {
        fun onFavouriteMovieClick(movie: Movie)
    }
}