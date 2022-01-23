package com.masliaiev.filmspace.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.masliaiev.filmspace.data.repository.MovieRepositoryImpl
import com.masliaiev.filmspace.domain.usecases.GetFavouriteMovieListUseCase

class FavouriteFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val getFavouritesMoviesUseCase = GetFavouriteMovieListUseCase(repository)

    val getFavouritesMovies = getFavouritesMoviesUseCase.invoke()

}