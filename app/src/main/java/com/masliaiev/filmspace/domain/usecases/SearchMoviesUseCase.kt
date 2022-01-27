package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository

class SearchMoviesUseCase(private val repository: MovieRepository) {
    fun searchMovies(lang: String, query: String): LiveData<PagingData<Movie>> {
        return repository.searchMovies(lang = lang, query = query)
    }
}