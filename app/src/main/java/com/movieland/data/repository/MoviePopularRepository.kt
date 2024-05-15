package com.movieland.data.repository

import android.graphics.Movie
import com.catnip.kokomputer.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    fun getPopularPlaying(): Flow<ResultWrapper<List<Movie>>>
}