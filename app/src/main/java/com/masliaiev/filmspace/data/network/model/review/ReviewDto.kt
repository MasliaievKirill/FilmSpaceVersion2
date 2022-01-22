package com.masliaiev.filmspace.data.network.model.review

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("author")
    @Expose
    val author: String?,
    @SerializedName("author_details")
    @Expose
    val authorDetails: ReviewAuthorDto?,
    @SerializedName("content")
    @Expose
    val content: String?,
    @SerializedName("created_at")
    @Expose
    val createdAt: String?,
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String?,
    @SerializedName("url")
    @Expose
    val url: String?
)