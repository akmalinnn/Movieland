package com.movieland.data.repository

import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceedFlow
import com.movieland.data.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    fun getPopularPlaying(): Flow<ResultWrapper<List<com.movieland.data.model.Movie>>>
}

//class MoviePopularRepositoryImpl(private val dataSource: MoviePopularDataSource) : MoviePopularRepository {
//    override fun getPopularPlaying(): Flow<ResultWrapper<List<Movie>>> {
//        return proceedFlow {
//            dataSource.getMovieList().data.toMovieList()
//        }
//            .onStart {
//                emit(ResultWrapper.Loading())
//                delay(1000)
//            }
//    }
//}