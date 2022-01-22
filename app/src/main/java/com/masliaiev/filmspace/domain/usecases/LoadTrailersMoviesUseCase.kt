package com.masliaiev.filmspace.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.repository.MovieRepository

class LoadTrailersMoviesUseCase (private val repository: MovieRepository) {
    operator fun invoke (){
        repository.loadTrailers()
    }
}