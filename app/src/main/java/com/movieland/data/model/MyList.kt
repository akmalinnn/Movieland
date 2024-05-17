package com.movieland.data.model

data class MyList (
    var id: Int? = null,
    var movieId: Int,
    var movieTitle: String,
    var movieDate: String,
    var movieRating: Double,
    var movieDesc: String,
    var movieImage: String,
    var movieBool: Boolean
)