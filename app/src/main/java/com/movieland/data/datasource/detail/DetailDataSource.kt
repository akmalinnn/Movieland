package com.movieland.data.datasource.detail


import com.movieland.data.source.network.model.detail.DetailMovieResponse

interface DetailDataSource {
    suspend fun detailMovies(movieId: Int): DetailMovieResponse
}