package com.movieland.data.datasource.detail

import com.movieland.data.source.network.model.detail.DetailMovieResponse
import com.movieland.data.source.network.services.MovielandApiService


class DetailDataSourceImpl(private val service: MovielandApiService): DetailDataSource {
    override suspend fun detailMovies(movieId: Int): DetailMovieResponse {
        return service.getMovieDetail(movieId)
    }
}