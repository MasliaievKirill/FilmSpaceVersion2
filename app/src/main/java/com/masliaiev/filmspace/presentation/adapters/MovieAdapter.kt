package com.masliaiev.filmspace.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.MovieItemBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.squareup.picasso.Picasso

class MovieAdapter :
    PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {


    var onMovieClickListener: OnMovieClickListener? = null
//    var onReachEndListener: OnReachEndListener? = null

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
//        if (movieList.size >= MAX_SIZE_OF_MOVIE_IN_ONE_PAGE && position == movieList.size - GAP && onReachEndListener != null){
//            onReachEndListener?.onReachEnd()
//        }
        val movie = getItem(position)
        Picasso.get().load(movie?.posterPath).placeholder(R.drawable.placeholder_large).into(holder.binding.imageViewSmallPoster)
        holder.binding.root.setOnClickListener {
            onMovieClickListener?.onMovieClick(movie!!.id)
        }
    }



    companion object {
        const val MAX_POOL_SIZE = 20
        private const val MAX_SIZE_OF_MOVIE_IN_ONE_PAGE = 20
        private const val GAP = 4
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieId: Int)
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }

}