package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository
import javax.inject.Inject

class GetSearchedMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(id: Int): LiveData<Movie> {
        return repository.getSearchedMovie(id)
    }
}