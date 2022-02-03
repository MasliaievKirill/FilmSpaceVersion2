package com.masliaiev.filmspace.data.mapper

import com.masliaiev.filmspace.data.database.FavouriteMovieDbModel
import com.masliaiev.filmspace.data.database.SearchedMovieDbModel
import com.masliaiev.filmspace.data.network.model.movie.MovieDto
import com.masliaiev.filmspace.data.network.model.search.SearchedMovieDto
import com.masliaiev.filmspace.data.network.model.trailer.TrailerDto
import com.masliaiev.filmspace.domain.entity.Movie
import com.masliaiev.filmspace.domain.entity.Trailer
import javax.inject.Inject

class MovieMapper @Inject constructor() {


    fun mapFavouriteMovieDbModelToMovieEntity(favouriteMovieDbModel: FavouriteMovieDbModel): Movie {
        return Movie(
            id = favouriteMovieDbModel.id,
            voteCount = favouriteMovieDbModel.voteCount,
            title = favouriteMovieDbModel.title,
            originalTitle = favouriteMovieDbModel.originalTitle,
            overview = favouriteMovieDbModel.overview,
            posterPath = favouriteMovieDbModel.posterPath,
            backdropPath = favouriteMovieDbModel.backdropPath,
            voteAverage = favouriteMovieDbModel.voteAverage,
            releaseDate = favouriteMovieDbModel.releaseDate
        )
    }

    fun mapSearchedMovieDbModelToMovieEntity(searchedMovieDbModel: SearchedMovieDbModel): Movie {
        return Movie(
            id = searchedMovieDbModel.id,
            voteCount = searchedMovieDbModel.voteCount,
            title = searchedMovieDbModel.title,
            originalTitle = searchedMovieDbModel.originalTitle,
            overview = searchedMovieDbModel.overview,
            posterPath = BASE_POSTER_URL + POSTER_SIZE + searchedMovieDbModel.posterPath,
            backdropPath = BASE_POSTER_URL + POSTER_SIZE + searchedMovieDbModel.backdropPath,
            voteAverage = searchedMovieDbModel.voteAverage,
            releaseDate = searchedMovieDbModel.releaseDate
        )
    }


    fun mapSearchedMovieDtoToMovieEntity(searchedMoviesDto: SearchedMovieDto): Movie {
        return Movie(
            id = searchedMoviesDto.id,
            voteCount = searchedMoviesDto.voteCount,
            title = searchedMoviesDto.title,
            originalTitle = searchedMoviesDto.originalTitle,
            overview = searchedMoviesDto.overview,
            posterPath = BASE_POSTER_URL + POSTER_SIZE + searchedMoviesDto.posterPath,
            backdropPath = BASE_POSTER_URL + POSTER_SIZE + searchedMoviesDto.backdropPath,
            voteAverage = searchedMoviesDto.voteAverage,
            releaseDate = searchedMoviesDto.releaseDate
        )
    }

    fun mapMovieDtoToMovieEntity(movieDto: MovieDto): Movie {
        return Movie(
            id = movieDto.id,
            voteCount = movieDto.voteCount,
            title = movieDto.title,
            originalTitle = movieDto.originalTitle,
            overview = movieDto.overview,
            posterPath = BASE_POSTER_URL + POSTER_SIZE + movieDto.posterPath,
            backdropPath = BASE_POSTER_URL + POSTER_SIZE + movieDto.backdropPath,
            voteAverage = movieDto.voteAverage,
            releaseDate = movieDto.releaseDate
        )
    }


    fun mapMovieEntityToFavouriteMovieDbModel(movie: Movie): FavouriteMovieDbModel {
        return FavouriteMovieDbModel(
            id = movie.id,
            voteCount = movie.voteCount,
            title = movie.title,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            voteAverage = movie.voteAverage,
            releaseDate = movie.releaseDate
        )
    }

    fun mapMovieEntityToSearchedMovieDbModel(movie: Movie): SearchedMovieDbModel {
        return SearchedMovieDbModel(
            id = movie.id,
            voteCount = movie.voteCount,
            title = movie.title,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            voteAverage = movie.voteAverage,
            releaseDate = movie.releaseDate
        )
    }

    fun mapTrailerDtoToTrailerEntity(trailerDto: TrailerDto): Trailer {
        return Trailer(
            key = BASE_YOUTUBE_URL + trailerDto.key,
            name = trailerDto.name,
            official = trailerDto.official
        )
    }


    companion object {
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
        private const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="
        private const val POSTER_SIZE = "w780"
    }


}