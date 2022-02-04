package com.masliaiev.filmspace.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.usecases.AddSearchedMovieUseCase
import com.masliaiev.filmspace.domain.usecases.GetSearchedMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.SearchMoviesUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    private val getSearchedMovieListUseCase: GetSearchedMovieListUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val addSearchedMovieUseCase: AddSearchedMovieUseCase
) : ViewModel() {

    var query: String = ""

    init {
        searchMovies(query)
    }

    val searchedMoviesListFromDb = getSearchedMovieListUseCase.invoke()


    var searchList: LiveData<PagingData<Movie>>? = null

    fun searchMovies(query: String){
        searchList = searchMoviesUseCase.searchMovies(getCurrentLanguage(), query)
            .cachedIn(viewModelScope)
    }


    fun addSearchedMovieInDb(movie: Movie) {
        viewModelScope.launch { addSearchedMovieUseCase.addSearchedMovie(movie) }

    }

    private fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }
}