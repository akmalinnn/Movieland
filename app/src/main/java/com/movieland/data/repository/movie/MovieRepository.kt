package com.movieland.data.repository.movie

import com.catnip.kokomputer.utils.ResultWrapper
import com.movieland.data.model.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun getNowPlaying(page: Int): Flow<ResultWrapper<List<Movie>>>
    fun getPopular(page: Int): Flow<ResultWrapper<List<Movie>>>
    fun getTopRating(page: Int): Flow<ResultWrapper<List<Movie>>>
    fun getUpcoming(page: Int): Flow<ResultWrapper<List<Movie>>>
}