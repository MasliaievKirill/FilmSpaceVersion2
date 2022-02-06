package com.masliaiev.filmspace.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.masliaiev.filmspace.data.database.MovieDao
import com.masliaiev.filmspace.data.mapper.MovieMapper
import com.masliaiev.filmspace.data.network.ApiService
import com.masliaiev.filmspace.data.network.PopularityMoviesPageSource
import com.masliaiev.filmspace.data.network.SearchMoviesPageSource
import com.masliaiev.filmspace.data.network.TopRatedMoviesPageSource
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.entity.Trailer
import com.masliaiev.filmspace.domain.repository.MovieRepository
import java.util.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val apiService: ApiService,
    private val mapper: MovieMapper
) : MovieRepository {


    override fun getFavouriteMovieList(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.getFavouriteMovieList()) {
            it.map {
                mapper.mapFavouriteMovieDbModelToMovieEntity(it)
            }
        }
    }

    override fun getSearchedMovieList(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.getSearchedMovieList()) {
            it.map {
                mapper.mapSearchedMovieDbModelToMovieEntity(it)
            }
        }
    }


    override suspend fun addFavouriteMovie(movie: Movie) {
        movieDao.addFavouriteMovie(mapper.mapMovieEntityToFavouriteMovieDbModel(movie))
    }

    override suspend fun addSearchedMovie(movie: Movie) {
        movieDao.addSearchedMovie(mapper.mapMovieEntityToSearchedMovieDbModel(movie))
    }

    override suspend fun deleteFavouriteMovie(id: Int) {
        movieDao.deleteFavouriteMovie(id)
    }

    override suspend fun deleteAllSearchedMovies() {
        movieDao.deleteAllSearchedMovies()
    }

    override fun loadPopularityMovies() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ), pagingSourceFactory = { PopularityMoviesPageSource(apiService, getCurrentLanguage()) }
    ).liveData

    override fun loadTopRatedMovies() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ), pagingSourceFactory = { TopRatedMoviesPageSource(apiService, getCurrentLanguage()) }
    ).liveData

    override suspend fun loadTrailers(movieId: Int): List<Trailer>? {
        var trailers: List<Trailer>? = null
        try {
            trailers = apiService.getTrailers(movieId = movieId.toString()).results?.let {
                it.map {
                    mapper.mapTrailerDtoToTrailerEntity(it)
                }
            }
        } catch (exception: Exception) {

        }
        return trailers

    }

    override fun searchMovies(lang: String, query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ), pagingSourceFactory = { SearchMoviesPageSource(apiService, lang, query) }
    ).liveData


    private fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }

}