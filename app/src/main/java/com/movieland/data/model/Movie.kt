package com.movieland.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    var id: Int,
    var title: String,
    var date: String,
    var rating: Double,
    var desc: String,
    var image: String,
    var banner: String
) : Parcelable