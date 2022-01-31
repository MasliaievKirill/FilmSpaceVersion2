package com.masliaiev.filmspace.presentation.view_models

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.masliaiev.filmspace.data.repository.MovieRepositoryImpl
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.usecases.GetFavouriteMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.GetMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.LoadMoviesUseCase
import kotlinx.coroutines.launch
import java.util.*

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val loadMoviesUseCase = LoadMoviesUseCase(repository)
    private val getFavouriteMovieListUseCase = GetFavouriteMovieListUseCase(repository)

    val moviesListPopularity = loadMovies(SORT_BY_POPULARITY)

    val moviesListTpoRated = loadMovies(SORT_BY_TOP_RATED)

    val favouriteMoviesList = getFavouriteMovieListUseCase.invoke()


    private fun loadMovies(sorted: String): LiveData<PagingData<Movie>> {
        return loadMoviesUseCase.loadMovies(sorted, getCurrentLanguage())
            .cachedIn(viewModelScope)
    }

    private fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }

    companion object{
        private const val SORT_BY_POPULARITY = "popularity.desc"
        private const val SORT_BY_TOP_RATED = "vote_average.desc"
    }


}