package com.movieland.data.mapper

import com.movieland.data.model.MovieDetail
import com.movieland.data.source.network.model.detail.DetailMovieResponse


fun DetailMovieResponse?.toDetail() =
   MovieDetail(
       id = this?.id ?: 0,
       title = this?.title.orEmpty(),
       date = this?.releaseDate.orEmpty(),
       rating = this?.voteAverage ?: 0.0,
       desc = this?.overview.orEmpty(),
       image = this?.posterPath.orEmpty(),
       banner = this?.backdropPath.orEmpty()
    )

fun Collection<DetailMovieResponse>?.toDetailMovie(): List<MovieDetail> = this?.map { it.toDetail() } ?: listOf()