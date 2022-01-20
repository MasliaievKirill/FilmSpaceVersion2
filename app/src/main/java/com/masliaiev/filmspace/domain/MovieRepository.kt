package com.masliaiev.filmspace.domain

import androidx.lifecycle.LiveData

interface MovieRepository {

    fun getMovieList(): LiveData<List<Movie>>

    fun getFavouriteMovieList(): LiveData<List<Movie>>

    fun getMovie(id: Int): LiveData<Movie>

    fun getFavouriteMovie(id: Int): LiveData<Movie>

    fun loadMovies()

    fun loadTrailers()

    fun loadReviews()

    fun searchMovies()

    fun addSearchedMovie()

    fun getSearchedMovieList(): LiveData<List<Movie>>
}