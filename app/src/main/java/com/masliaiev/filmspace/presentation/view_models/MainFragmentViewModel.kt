package com.masliaiev.filmspace.presentation.view_models

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.masliaiev.filmspace.data.repository.MovieRepositoryImpl
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.usecases.GetMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.LoadMoviesUseCase
import kotlinx.coroutines.launch
import java.util.*

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val loadMoviesUseCase = LoadMoviesUseCase(repository)

    lateinit var moviesList: LiveData<PagingData<Movie>>

    fun loadMovies(sorted: String) {
        moviesList = loadMoviesUseCase.loadMovies(sorted, getCurrentLanguage(), 1)
            .cachedIn(viewModelScope)
    }

    private fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }


}