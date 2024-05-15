package com.movieland.data.repository

import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceedFlow
import com.movieland.data.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

interface MovieTopRatedRepository {
    fun getTopRatedPlaying(): Flow<ResultWrapper<List<Movie>>>
}

//class MovieTopRatedRepositoryImpl(private val dataSource: MovieTopRatedDataSource) : MovieTopRatedRepository {
//    override fun getTopRatedPlaying(): Flow<ResultWrapper<List<Movie>>> {
//        return proceedFlow {
//            dataSource.getMovieList().data.toMovieList()
//        }
//            .onStart {
//                emit(ResultWrapper.Loading())
//                delay(1000)
//            }
//    }
//}