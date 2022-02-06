package com.masliaiev.filmspace.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masliaiev.filmspace.R
import com.masliaiev.filmspace.databinding.MovieItemBinding
import com.masliaiev.filmspace.domain.entity.Movie
import com.squareup.picasso.Picasso

class MoviePagerAdapter :
    PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {


    var onMovieClickListener: OnMovieClickListener? = null

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
                onMovieClickListener?.onMovieClick(movie)
            }
        }
    }

    override fun findRelativeAdapterPositionIn(
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
        viewHolder: RecyclerView.ViewHolder,
        localPosition: Int
    ): Int {
        return super.findRelativeAdapterPositionIn(adapter, viewHolder, localPosition)
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

}