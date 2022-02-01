package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun loadMovies(sorted: String, lang: String): LiveData<PagingData<Movie>> {
        return repository.loadMovies(sorted = sorted, lang = lang)
    }
}