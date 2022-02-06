package com.masliaiev.filmspace.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.masliaiev.filmspace.domain.usecases.LoadPopularityMoviesUseCase
import com.masliaiev.filmspace.domain.usecases.LoadTopRatedMoviesUseCase
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val loadPopularityMoviesUseCase: LoadPopularityMoviesUseCase,
    private val loadTopRatedMoviesUseCase: LoadTopRatedMoviesUseCase
) : ViewModel() {


    val popularityMoviesList = loadPopularityMoviesUseCase.loadPopularityMovies()
        .cachedIn(viewModelScope)

    val topRatedMoviesList = loadTopRatedMoviesUseCase.loadTopRatedMovies()
        .cachedIn(viewModelScope)

}