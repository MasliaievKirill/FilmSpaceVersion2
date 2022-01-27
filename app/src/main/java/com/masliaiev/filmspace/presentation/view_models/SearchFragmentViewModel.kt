package com.masliaiev.filmspace.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.masliaiev.filmspace.data.repository.MovieRepositoryImpl
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.usecases.AddSearchedMovieUseCase
import com.masliaiev.filmspace.domain.usecases.GetSearchedMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.GetSearchedMovieUseCase
import com.masliaiev.filmspace.domain.usecases.SearchMoviesUseCase
import kotlinx.coroutines.launch
import java.util.*

class SearchFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val getSearchedMovieListUseCase = GetSearchedMovieListUseCase(repository)
    private val searchMoviesUseCase = SearchMoviesUseCase(repository)
    private val addSearchedMovieUseCase = AddSearchedMovieUseCase(repository)
    private val getSearchedMovieUseCase = GetSearchedMovieUseCase(repository)


    val searchedMoviesListFromDb = getSearchedMovieListUseCase.invoke()

    fun loadMovies(query: String): LiveData<PagingData<Movie>> {
        return searchMoviesUseCase.searchMovies(getCurrentLanguage(),query)
            .cachedIn(viewModelScope)
    }

    fun addSearchedMovieInDb (movie: Movie) {
        viewModelScope.launch { addSearchedMovieUseCase.addSearchedMovie(movie) }

    }

    private fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }
}