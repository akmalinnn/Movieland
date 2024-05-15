package com.movieland.data.repository

import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceedFlow
import com.movieland.data.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

interface MovieNowPlayingRepository {
    fun getMoviesNowPlaying(): Flow<ResultWrapper<List<com.movieland.data.model.Movie>>>
}

//class MovieNowPlayingRepositoryImpl(private val dataSource: MovieNowPlayingDataSource) : MovieNowPlayingRepository {
//    override fun getMoviesNowPlaying(): Flow<ResultWrapper<List<Movie>>> {
//        return proceedFlow {
//            dataSource.getMovieList().data.toMovieList()
//        }
//            .onStart {
//                emit(ResultWrapper.Loading())
//                delay(1000)
//            }
//    }
//}