package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class AddSearchedMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun addSearchedMovie(movie: Movie) {
        repository.addSearchedMovie(movie)
    }
}