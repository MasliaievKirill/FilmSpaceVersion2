package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class LoadMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun loadMovies(sorted: String, lang: String, page: String) {
        return repository.loadMovies(sorted = sorted, lang = lang, page = page)
    }
}