package com.masliaiev.filmspace.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.masliaiev.filmspace.data.repository.MovieRepositoryImpl
import com.masliaiev.filmspace.domain.usecases.GetMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.LoadMoviesUseCase
import kotlinx.coroutines.launch
import java.util.*

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val loadMoviesUseCase = LoadMoviesUseCase(repository)
    private val getMoviesListUseCase = GetMovieListUseCase(repository)

    val getMovies = getMoviesListUseCase.invoke()

    fun loadMovies(popularity: Boolean, page: Int) {
        viewModelScope.launch {
            loadMoviesUseCase.loadMovies(
                popularity = popularity,
                lang = getCurrentLanguage(),
                page = page
            )
        }
    }

    private fun getCurrentLanguage(): String {
        return Locale.getDefault().language

    }


}