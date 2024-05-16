package com.movieland.data.source.network.model.movieNowPlaying


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DatesNowPlayingResponse(
    @SerializedName("maximum")
    var maximum: String?,
    @SerializedName("minimum")
    var minimum: String?
)