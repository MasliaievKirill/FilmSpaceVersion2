package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository

class AddSearchedMovieUseCase (private val repository: MovieRepository) {
    suspend fun addSearchedMovie (movie: Movie){
        repository.addSearchedMovie(movie)
    }
}