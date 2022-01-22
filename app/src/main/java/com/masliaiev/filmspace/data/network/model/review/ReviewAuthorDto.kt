package com.masliaiev.filmspace.data.network.model.review

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewAuthorDto(
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("username")
    @Expose
    val username: String?,
    @SerializedName("avatar_path")
    @Expose
    val avatarPath: Any?,
    @SerializedName("rating")
    @Expose
    val rating: Double?
)
