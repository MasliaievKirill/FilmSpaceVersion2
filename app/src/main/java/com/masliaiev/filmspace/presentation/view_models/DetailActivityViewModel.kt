package com.masliaiev.filmspace.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.masliaiev.filmspace.data.repository.MovieRepositoryImpl
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.entity.Review
import com.masliaiev.filmspace.domain.entity.Trailer
import com.masliaiev.filmspace.domain.usecases.*
import kotlinx.coroutines.launch

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val addFavouriteMovieUseCase = AddFavouriteMovieUseCase(repository)
    private val deleteFavouriteMovieUseCase = DeleteFavouriteMovieUseCase(repository)
    private val getFavouriteMovieListUseCase = GetFavouriteMovieListUseCase(repository)
    private val loadTrailersMoviesUseCase = LoadTrailersMoviesUseCase(repository)
    private val loadReviewsUseCase = LoadReviewsUseCase(repository)

    val favouriteMoviesList = getFavouriteMovieListUseCase.invoke()

    private var _trailersList = MutableLiveData<List<Trailer>>()
    val trailersList: LiveData<List<Trailer>>
        get() = _trailersList

    private var _reviewsList = MutableLiveData<List<Review>>()
    val reviewsList: LiveData<List<Review>>
        get() = _reviewsList


    fun addMovieToFavourite(movie: Movie) {
        viewModelScope.launch { addFavouriteMovieUseCase.addFavouriteMovie(movie) }
    }

    fun deleteMovieFromFavourite(id: Int) {
        viewModelScope.launch { deleteFavouriteMovieUseCase.deleteFavouriteMovie(id) }
    }

    fun loadTrailers(id: Int) {
        viewModelScope.launch { _trailersList.value = loadTrailersMoviesUseCase.loadTrailers(id) }
    }

    fun loadReviews(id: Int) {
        viewModelScope.launch { _reviewsList.value = loadReviewsUseCase.loadReviews(id) }
    }


}