package com.movieland.data.datasource.movie

import com.movieland.data.source.network.model.movieNowPlaying.NowPlayingResponse
import com.movieland.data.source.network.model.moviePopular.PopularMovieResponse
import com.movieland.data.source.network.model.movieTopRating.TopRatedResponse
import com.movieland.data.source.network.model.movieUpcoming.UpcomingMovieResponse
import com.movieland.data.source.network.services.MovielandApiService

class MovieApiDataSource(private val service: MovielandApiService) : MovieDataSource {
    override suspend fun getNowPlaying(page: Int): NowPlayingResponse {
        return service.nowPlaying(page)
    }

    override suspend fun getPopular(page: Int): PopularMovieResponse {
        return service.popular(page)
    }

    override suspend fun getTopRating(page: Int): TopRatedResponse {
        return service.topRated(page)
    }

    override suspend fun getUpcoming(page: Int): UpcomingMovieResponse {
        return service.upcoming(page)
    }
}