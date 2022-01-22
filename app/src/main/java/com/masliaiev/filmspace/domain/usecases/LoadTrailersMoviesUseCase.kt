package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.entity.Trailer
import com.masliaiev.filmspace.domain.repository.MovieRepository

class LoadTrailersMoviesUseCase (private val repository: MovieRepository) {
    suspend fun loadTrailers(movieId: Int): List<Trailer>?{
        return repository.loadTrailers(movieId)
    }
}