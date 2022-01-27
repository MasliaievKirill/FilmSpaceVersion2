package com.masliaiev.filmspace.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.masliaiev.filmspace.data.network.model.movie.MovieDto
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

    fun loadMovies(sorted: String, lang: String): LiveData<PagingData<Movie>>

    suspend fun loadTrailers(movieId: Int): List<Trailer>?

    suspend fun loadReviews(movieId: Int): List<Review>?

    fun searchMovies(lang: String, query: String): LiveData<PagingData<Movie>>

}