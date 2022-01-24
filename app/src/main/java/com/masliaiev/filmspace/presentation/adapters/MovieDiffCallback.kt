package com.masliaiev.filmspace.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.masliaiev.filmspace.domain.entity.Movie

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}