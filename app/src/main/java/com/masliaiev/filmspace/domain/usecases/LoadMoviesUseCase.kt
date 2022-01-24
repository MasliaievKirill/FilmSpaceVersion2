package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository

class LoadMoviesUseCase(private val repository: MovieRepository) {
    fun loadMovies(sorted: String, lang: String, page: Int): LiveData<PagingData<Movie>> {
        return repository.loadMovies(sorted = sorted, lang = lang, page = page)
    }
}