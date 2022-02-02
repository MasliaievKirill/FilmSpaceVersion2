package com.masliaiev.filmspace.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.entity.Trailer

interface MovieRepository {

    fun getFavouriteMovieList(): LiveData<List<Movie>>

    fun getSearchedMovieList(): LiveData<List<Movie>>

    suspend fun addFavouriteMovie(movie: Movie)

    suspend fun addSearchedMovie(movie: Movie)

    suspend fun deleteFavouriteMovie(id: Int)

    fun loadPopularityMovies(): LiveData<PagingData<Movie>>

    fun loadTopRatedMovies(): LiveData<PagingData<Movie>>

    suspend fun loadTrailers(movieId: Int): List<Trailer>?

    fun searchMovies(lang: String, query: String): LiveData<PagingData<Movie>>

}