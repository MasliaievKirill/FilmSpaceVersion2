package com.masliaiev.filmspace.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.masliaiev.filmspace.domain.entity.Movie

class MovieListDiffCallback(
    private val oldList: List<Movie>,
    private val newList: List<Movie>,
): DiffUtil.Callback() {



    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}