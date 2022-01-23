package com.masliaiev.filmspace.domain.repository

import androidx.lifecycle.LiveData
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.entity.Review
import com.masliaiev.filmspace.domain.entity.Trailer

interface MovieRepository {

    fun getMovieList(): LiveData<List<Movie>>

    fun getFavouriteMovieList(): LiveData<List<Movie>>

    fun getSearchedMovieList(): LiveData<List<Movie>>

    fun getMovie(id: Int): LiveData<Movie>

    fun getFavouriteMovie(id: Int): LiveData<Movie>

    fun getSearchedMovie(id: Int): LiveData<Movie>

    suspend fun addFavouriteMovie(movie: Movie)

    suspend fun addSearchedMovie(movie: Movie)

    suspend fun deleteFavouriteMovie(id: Int)

    suspend fun loadMovies(popularity: Boolean, lang: String, page: Int): Boolean

    suspend fun loadTrailers(movieId: Int): List<Trailer>?

    suspend fun loadReviews(movieId: Int): List<Review>?

    suspend fun searchMovies(lang: String, query: String, page: Int): List<Movie>?

}