package com.masliaiev.filmspace.domain.usecases

import com.masliaiev.filmspace.domain.entity.Trailer
import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class LoadTrailersMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun loadTrailers(movieId: Int): List<Trailer>? {
        return repository.loadTrailers(movieId)
    }
}