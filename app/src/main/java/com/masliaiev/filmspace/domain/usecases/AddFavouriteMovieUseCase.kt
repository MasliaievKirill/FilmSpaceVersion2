package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class AddFavouriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun addFavouriteMovie(movie: Movie) {
        repository.addFavouriteMovie(movie)
    }
}