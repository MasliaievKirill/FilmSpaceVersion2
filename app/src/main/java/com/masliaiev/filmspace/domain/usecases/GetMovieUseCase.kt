package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository

class GetMovieUseCase (private val repository: MovieRepository) {
    operator fun invoke (id: Int): LiveData<Movie> {
        return repository.getMovie(id)
    }
}