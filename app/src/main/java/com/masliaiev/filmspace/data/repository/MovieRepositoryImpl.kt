package com.masliaiev.filmspace.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.masliaiev.filmspace.data.database.AppDatabase
import com.masliaiev.filmspace.data.mapper.MovieMapper
import com.masliaiev.filmspace.data.network.ApiFactory
import com.masliaiev.filmspace.data.network.MoviesPageSource
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.entity.Review
import com.masliaiev.filmspace.domain.entity.Trailer
import com.masliaiev.filmspace.domain.repository.MovieRepository

class MovieRepositoryImpl(private val application: Application) : MovieRepository {

    private val movieDao = AppDatabase.getInstance(application).movieDao()
    private val apiService = ApiFactory.apiService

    private val mapper = MovieMapper()


    override fun getMovieList(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.getMovieList()) {
            it.map {
                mapper.mapMovieDbModelToMovieEntity(it)
            }
        }
    }

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

    override fun getMovie(id: Int): LiveData<Movie> {
        return Transformations.map(movieDao.getMovie(id)) {
            mapper.mapMovieDbModelToMovieEntity(it)
        }
    }

    override fun getFavouriteMovie(id: Int): LiveData<Movie> {
        return Transformations.map(movieDao.getFavouriteMovie(id)) {
            mapper.mapFavouriteMovieDbModelToMovieEntity(it)
        }
    }

    override fun getSearchedMovie(id: Int): LiveData<Movie> {
        return Transformations.map(movieDao.getSearchedMovie(id)) {
            mapper.mapSearchedMovieDbModelToMovieEntity(it)
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

    override fun loadMovies(sorted: String, lang: String, page: Int) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ), pagingSourceFactory = {MoviesPageSource(apiService, lang, sorted)}
    ).liveData
//        try {
//            val movies = if (popularity) {
//                apiService.getPopularityMovies(lang = lang, page = page.toString())
//            } else {
//                apiService.getTopRatedMovies(lang = lang, page = page.toString())
//            }
//            if (page == 1) {
//                movieDao.deleteAllMovies()
//            }
//            movies.results?.let {
//                movieDao.insertMovieList(it.map {
//                    mapper.mapMovieDtoToMovieDbModel(it)
//                })
//            }
//        } catch (e: Exception) {
//            return false
//        }
//        return true


    override suspend fun loadTrailers(movieId: Int): List<Trailer>? {
        val trailers = apiService.getTrailers(movieId = movieId.toString()).results?.let {
            it.map {
                mapper.mapTrailerDtoToTrailerEntity(it)
            }
        }
        return trailers
    }

    override suspend fun loadReviews(movieId: Int): List<Review>? {
        val reviews = apiService.getReviews(movieId = movieId.toString()).results?.let {
            it.map {
                mapper.mapReviewDtoToReviewEntity(it)
            }
        }
        return reviews
    }

    override suspend fun searchMovies(lang: String, query: String, page: Int): List<Movie>? {
        val searchedMovies =
            apiService.searchMovie(lang = lang, query = query, page = page.toString()).results?.let {
                it.map {
                    mapper.mapSearchedMovieDtoToMovieEntity(it)
                }
            }
        return searchedMovies
    }

}