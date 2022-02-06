package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class DeleteAllSearchedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun deleteAllSearchedMovies(){
        repository.deleteAllSearchedMovies()
    }
}