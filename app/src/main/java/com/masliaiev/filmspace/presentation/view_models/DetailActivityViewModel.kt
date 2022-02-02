package com.masliaiev.filmspace.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.entity.Trailer
import com.masliaiev.filmspace.domain.usecases.AddFavouriteMovieUseCase
import com.masliaiev.filmspace.domain.usecases.DeleteFavouriteMovieUseCase
import com.masliaiev.filmspace.domain.usecases.GetFavouriteMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.LoadTrailersMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val deleteFavouriteMovieUseCase: DeleteFavouriteMovieUseCase,
    private val getFavouriteMovieListUseCase: GetFavouriteMovieListUseCase,
    private val loadTrailersMoviesUseCase: LoadTrailersMoviesUseCase
) : ViewModel() {


    val favouriteMoviesList = getFavouriteMovieListUseCase.invoke()

    private var _trailersList = MutableLiveData<List<Trailer>>()
    val trailersList: LiveData<List<Trailer>>
        get() = _trailersList


    fun addMovieToFavourite(movie: Movie) {
        viewModelScope.launch { addFavouriteMovieUseCase.addFavouriteMovie(movie) }
    }

    fun deleteMovieFromFavourite(id: Int) {
        viewModelScope.launch { deleteFavouriteMovieUseCase.deleteFavouriteMovie(id) }
    }

    fun loadTrailers(id: Int) {
        viewModelScope.launch { _trailersList.value = loadTrailersMoviesUseCase.loadTrailers(id) }
    }


}