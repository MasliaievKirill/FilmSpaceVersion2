package com.masliaiev.filmspace.data.network.model.trailer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailersListDto(

    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("results")
    @Expose
    val results: List<TrailerDto>?
)