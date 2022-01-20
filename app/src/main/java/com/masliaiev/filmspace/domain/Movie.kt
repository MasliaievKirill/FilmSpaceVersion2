package com.masliaiev.filmspace.domain

data class Movie(
    val id: Int,
    val voteCount: Int?,
    val title: String,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double?,
    val releaseDate: String?
)