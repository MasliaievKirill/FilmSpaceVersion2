package com.masliaiev.filmspace.data.network.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchedMoviesListDto(
    @SerializedName("page")
    @Expose
    val page: Int?,

    @SerializedName("results")
    @Expose
    val results: List<SearchedMovieDto>?,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int?,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int?
)

