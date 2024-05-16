package com.movieland.data.datasource.movie

import com.movieland.data.source.network.model.movieNowPlaying.NowPlayingResponse
import com.movieland.data.source.network.model.moviePopular.PopularMovieResponse
import com.movieland.data.source.network.model.movieTopRating.TopRatedResponse
import com.movieland.data.source.network.model.movieUpcoming.UpcomingMovieResponse

interface MovieDataSource {
    suspend fun getNowPlaying(page: Int): NowPlayingResponse
    suspend fun getPopular(page: Int): PopularMovieResponse
    suspend fun getTopRating(page: Int): TopRatedResponse
    suspend fun getUpcoming(page: Int): UpcomingMovieResponse
}