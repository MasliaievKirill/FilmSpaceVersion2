package com.masliaiev.filmspace.domain.repository

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie

interface MovieRepository {

    fun getMovieList(): LiveData<List<Movie>>

    fun getFavouriteMovieList(): LiveData<List<Movie>>

    fun getSearchedMovieList(): LiveData<List<Movie>>

    fun getMovie(id: Int): LiveData<Movie>

    fun getFavouriteMovie(id: Int): LiveData<Movie>

    fun getSearchedMovie(id: Int): LiveData<Movie>

    fun addFavouriteMovie(movie: Movie)

    fun addSearchedMovie(movie: Movie)

    fun deleteFavouriteMovie(movie: Movie)

    fun loadMovies()

    fun loadTrailers()

    fun loadReviews()

    fun searchMovies()

}