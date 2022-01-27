package com.masliaiev.filmspace.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searched_movies")
data class SearchedMovieDbModel (
    @PrimaryKey
    val id: Int,
    val voteCount: Int?,
    val title: String?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double?,
    val releaseDate: String?
)