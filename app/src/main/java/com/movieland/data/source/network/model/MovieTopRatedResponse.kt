package com.movieland.data.source.network.model

import com.google.gson.annotations.SerializedName

data class MovieTopRatedResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val data: List<MovieListResponse>,
)