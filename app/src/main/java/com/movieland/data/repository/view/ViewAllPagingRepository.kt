package com.movieland.data.repository.view

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.movieland.data.model.Movie
import com.movieland.data.paging.NowPlayingPagingSource
import com.movieland.data.paging.PopularPagingSource
import com.movieland.data.paging.TopRatedPagingSource
import com.movieland.data.paging.UpComingPagingSource
import com.movieland.data.source.network.services.MovielandApiService
import kotlinx.coroutines.flow.Flow

interface ViewAllPagingRepository {
    fun getTopRatedList(): Flow<PagingData<Movie>>

    fun getUpComingList(): Flow<PagingData<Movie>>

    fun getPopularList(): Flow<PagingData<Movie>>

    fun getNowPlayingList(): Flow<PagingData<Movie>>
}

class ViewAllPagingRepositoryImpl(private val service: MovielandApiService) : ViewAllPagingRepository {
    override fun getTopRatedList(): Flow<PagingData<Movie>> =
        Pager(
            pagingSourceFactory = { TopRatedPagingSource(service) },
            config =
            PagingConfig(
                pageSize = 20,
            ),
        ).flow

    override fun getUpComingList(): Flow<PagingData<Movie>> =
        Pager(
            pagingSourceFactory = { UpComingPagingSource(service) },
            config =
            PagingConfig(
                pageSize = 20,
            ),
        ).flow

    override fun getPopularList(): Flow<PagingData<Movie>> =
        Pager(
            pagingSourceFactory = { PopularPagingSource(service) },
            config =
            PagingConfig(
                pageSize = 20,
            ),
        ).flow

    override fun getNowPlayingList(): Flow<PagingData<Movie>> =
        Pager(
            pagingSourceFactory = { NowPlayingPagingSource(service) },
            config =
            PagingConfig(
                pageSize = 20,
            ),
        ).flow
}