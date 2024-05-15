package com.movieland.data.repository

import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceedFlow
import com.movieland.data.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

interface MovieUpComingRepository {
    fun getUpComingPlaying(): Flow<ResultWrapper<List<Movie>>>
}

//class MovieUpComingRepositoryImpl(private val dataSource: MovieUpComingDataSource) : MovieUpComingRepository {
//    override fun getUpComingPlaying(): Flow<ResultWrapper<List<Movie>>> {
//        return proceedFlow {
//            dataSource.getMovieList().data.toMovieList()
//        }
//            .onStart {
//                emit(ResultWrapper.Loading())
//                delay(1000)
//            }
//    }
//}