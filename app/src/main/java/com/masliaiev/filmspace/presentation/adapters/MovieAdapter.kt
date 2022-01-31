package com.masliaiev.filmspace.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.MovieItemBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.squareup.picasso.Picasso

class MovieAdapter :
    ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {


    var onMovieClickListener: OnMovieClickListener? = null
    var onReachEndListener: OnReachEndListener? = null

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
        if (onReachEndListener != null &&
            currentList.size >= MAX_SIZE_OF_MOVIE_IN_ONE_PAGE &&
            position >= currentList.size - GAP
        ) {
            onReachEndListener?.onReachEnd()
        }
        val movie = getItem(position)
        Picasso.get().load(movie?.posterPath).placeholder(R.drawable.placeholder_large)
            .into(holder.binding.ivSmallPoster)
        holder.binding.root.setOnClickListener {
            onMovieClickListener?.onMovieClick(movie!!)
        }
    }


    companion object {
        const val MAX_POOL_SIZE = 20
        private const val MAX_SIZE_OF_MOVIE_IN_ONE_PAGE = 20
        private const val GAP = 4
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }


}