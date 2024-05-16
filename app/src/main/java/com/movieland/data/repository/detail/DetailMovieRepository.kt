package com.movieland.data.repository.detail

import com.catnip.kokomputer.utils.ResultWrapper
import com.movieland.data.model.Movie
import com.movieland.data.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {
    fun detailMovies(movieId: Int): Flow<ResultWrapper<MovieDetail>>
}