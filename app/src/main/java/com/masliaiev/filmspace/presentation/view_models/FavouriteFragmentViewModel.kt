package com.masliaiev.filmspace.presentation.view_models

import androidx.lifecycle.ViewModel
import com.masliaiev.filmspace.domain.usecases.GetFavouriteMovieListUseCase
import javax.inject.Inject

class FavouriteFragmentViewModel @Inject constructor(
    private val getFavouritesMoviesUseCase: GetFavouriteMovieListUseCase
) : ViewModel() {

    val getFavouritesMovies = getFavouritesMoviesUseCase.invoke()

}