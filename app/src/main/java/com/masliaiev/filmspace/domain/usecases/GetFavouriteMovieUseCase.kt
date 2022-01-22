package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository

class GetFavouriteMovieUseCase (private val repository: MovieRepository) {
    operator fun invoke (id: Int): LiveData<Movie> {
        return repository.getFavouriteMovie(id)
    }
}