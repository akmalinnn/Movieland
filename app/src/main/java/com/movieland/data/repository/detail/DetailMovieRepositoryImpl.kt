package com.movieland.data.repository.detail


import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceedFlow
import com.movieland.data.datasource.detail.DetailDataSource
import com.movieland.data.mapper.toDetail
import com.movieland.data.model.MovieDetail
import kotlinx.coroutines.flow.Flow

class DetailMovieRepositoryImpl(private val dataSource: DetailDataSource): DetailMovieRepository {
    override fun detailMovies(movieId: Int): Flow<ResultWrapper<MovieDetail>> {
        return proceedFlow {
            dataSource.detailMovies(movieId).toDetail()
        }
    }
}