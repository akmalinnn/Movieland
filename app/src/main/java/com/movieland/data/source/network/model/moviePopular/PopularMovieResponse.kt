package com.movieland.data.source.network.model.moviePopular


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.movieland.data.source.network.model.movieNowPlaying.DatesNowPlayingResponse
import com.movieland.data.source.network.model.movieNowPlaying.ResultNowPlayingResponse


@Keep
data class PopularMovieResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<ResultPopularMovieResponse>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)