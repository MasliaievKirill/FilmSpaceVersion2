package com.masliaiev.filmspace.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.masliaiev.filmspace.data.repository.MovieRepositoryImpl
import com.masliaiev.filmspace.domain.usecases.AddSearchedMovieUseCase
import com.masliaiev.filmspace.domain.usecases.GetSearchedMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.SearchMoviesUseCase

class SearchFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val getSearchedMovieListUseCase = GetSearchedMovieListUseCase(repository)
    private val searchMoviesUseCase = SearchMoviesUseCase(repository)
    private val addSearchedMovieUseCase = AddSearchedMovieUseCase(repository)
}