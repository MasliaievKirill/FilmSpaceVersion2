package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository

class AddFavouriteMovieUseCase (private val repository: MovieRepository) {
    suspend fun addFavouriteMovie (movie: Movie){
        repository.addFavouriteMovie(movie)
    }
}