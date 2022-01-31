package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class DeleteFavouriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun deleteFavouriteMovie(id: Int) {
        repository.deleteFavouriteMovie(id)
    }
}