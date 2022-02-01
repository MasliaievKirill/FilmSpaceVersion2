package com.masliaiev.filmspace.presentation.view_models

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.usecases.DeleteAllMoviesUseCase
import com.masliaiev.filmspace.domain.usecases.GetFavouriteMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.GetMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.LoadMoviesUseCase
import java.util.*
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getFavouriteMovieListUseCase: GetFavouriteMovieListUseCase,
    private val deleteAllMoviesUseCase: DeleteAllMoviesUseCase
) : ViewModel() {


    val moviesList = loadMoviesUseCase.loadMovies(SORT_BY_POPULARITY, getCurrentLanguage())
        .cachedIn(viewModelScope)



    private fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }

    companion object {
        private const val SORT_BY_POPULARITY = "popularity.desc"
        private const val SORT_BY_TOP_RATED = "vote_average.desc"
    }


}