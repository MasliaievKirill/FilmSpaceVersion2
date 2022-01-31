package com.masliaiev.filmspace.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masliaiev.filmspace.domain.usecases.GetFavouriteMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.GetMovieListUseCase
import com.masliaiev.filmspace.domain.usecases.LoadMoviesUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getFavouriteMovieListUseCase: GetFavouriteMovieListUseCase
) : ViewModel() {


    val moviesList = getMovieListUseCase.invoke()

    val favouriteMoviesList = getFavouriteMovieListUseCase.invoke()


    fun loadMovies(sorted: String, page: String) {
        viewModelScope.launch {
            loadMoviesUseCase.loadMovies(sorted = sorted, lang = getCurrentLanguage(), page = page)
        }
    }

    private fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }

    companion object {
        private const val SORT_BY_POPULARITY = "popularity.desc"
        private const val SORT_BY_TOP_RATED = "vote_average.desc"
    }


}