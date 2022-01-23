package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.repository.MovieRepository

class LoadMoviesUseCase(private val repository: MovieRepository) {
    suspend fun loadMovies(popularity: Boolean, lang: String, page: Int): Boolean  {
        return repository.loadMovies(popularity = popularity, lang = lang, page = page)
    }
}