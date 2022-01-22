package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository

class SearchMoviesUseCase(private val repository: MovieRepository) {
    suspend fun searchMovies(lang: String, query: String, page: Int): List<Movie>? {
        return repository.searchMovies(lang = lang, query = query, page = page)
    }
}