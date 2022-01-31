package com.masliaiev.filmspace.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.MovieItemBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.squareup.picasso.Picasso

class MoviePagingAdapter :
    PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {


    var onMovieClickListener: OnMovieClickListener? = null
    var favouriteMoviesList: List<Movie>? = null

    private var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d("Adapter", "onCreateViewHolder ${++count}")
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
//        Log.d("Adapter", "onBindViewHolder ${++count}")
        val movie = getItem(position)
        if (favouriteMoviesList != null){
            var inFavourite = false
            for (favouriteMovie in favouriteMoviesList!!){
                if (favouriteMovie == movie){
                    inFavourite = true
                }
            }
            if (inFavourite){
                holder.binding.ivFavouriteIndicator.visibility = View.VISIBLE
            } else {
                holder.binding.ivFavouriteIndicator.visibility = View.INVISIBLE
            }
        }
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


}