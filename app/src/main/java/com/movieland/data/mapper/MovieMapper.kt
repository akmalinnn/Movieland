package com.movieland.data.mapper

import com.movieland.data.model.Movie
import com.movieland.data.source.network.model.detail.DetailMovieResponse
import com.movieland.data.source.network.model.movieNowPlaying.ResultNowPlayingResponse
import com.movieland.data.source.network.model.moviePopular.ResultPopularMovieResponse
import com.movieland.data.source.network.model.movieTopRating.ResultTopRatedResponse
import com.movieland.data.source.network.model.movieUpcoming.ResultUpcomingMovieResponse

fun ResultNowPlayingResponse?.toMovieNowPlaying() =
    Movie(
        id = this?.id ?: 0,
        title = this?.title.orEmpty(),
        date = this?.releaseDate.orEmpty(),
        rating = this?.voteAverage ?: 0.0,
        desc = this?.overview.orEmpty(),
        image = this?.posterPath.orEmpty(),
        banner = this?.backdropPath.orEmpty()
    )

fun ResultPopularMovieResponse?.toPopularMovie() =
    Movie(
        id = this?.id ?: 0,
        title = this?.title.orEmpty(),
        date = this?.releaseDate.orEmpty(),
        rating = this?.voteAverage ?: 0.0,
        desc = this?.overview.orEmpty(),
        image = this?.posterPath.orEmpty(),
        banner = this?.backdropPath.orEmpty()
    )

fun ResultTopRatedResponse?.toTopRatedMovie() =
    Movie(
        id = this?.id ?: 0,
        title = this?.title.orEmpty(),
        date = this?.releaseDate.orEmpty(),
        rating = this?.voteAverage ?: 0.0,
        desc = this?.overview.orEmpty(),
        image = this?.posterPath.orEmpty(),
        banner = this?.backdropPath.orEmpty()
    )

fun ResultUpcomingMovieResponse?.toUpcomingMovie() =
    Movie(
        id = this?.id ?: 0,
        title = this?.title.orEmpty(),
        date = this?.releaseDate.orEmpty(),
        rating = this?.voteAverage ?: 0.0,
        desc = this?.overview.orEmpty(),
        image = this?.posterPath.orEmpty(),
        banner = this?.backdropPath.orEmpty()
    )


fun Collection<ResultNowPlayingResponse>?.toMovieNowPlayed(): List<Movie> =
    this?.map { it.toMovieNowPlaying() } ?: listOf()

fun Collection<ResultPopularMovieResponse>?.toMoviePopular(): List<Movie> =
    this?.map { it.toPopularMovie() } ?: listOf()

fun Collection<ResultTopRatedResponse>?.toMovieTopRated(): List<Movie> =
    this?.map { it.toTopRatedMovie() } ?: listOf()

fun Collection<ResultUpcomingMovieResponse>?.toMovieUpcoming(): List<Movie> =
    this?.map { it.toUpcomingMovie() } ?: listOf()

