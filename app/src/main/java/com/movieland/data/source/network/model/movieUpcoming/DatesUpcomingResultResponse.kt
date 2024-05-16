package com.movieland.data.source.network.model.movieUpcoming


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DatesUpcomingResultResponse(
    @SerializedName("maximum")
    var maximum: String?,
    @SerializedName("minimum")
    var minimum: String?
)