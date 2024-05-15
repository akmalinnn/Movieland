package com.movieland.data.repository

import android.graphics.Movie
import com.catnip.kokomputer.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MovieNowPlayingRepository {
    fun getMoviesNowPlaying(): Flow<ResultWrapper<List<Movie>>>
}