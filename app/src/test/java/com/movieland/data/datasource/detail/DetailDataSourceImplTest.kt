package com.movieland.data.datasource.detail

import com.movieland.data.datasource.movie.MovieApiDataSource
import com.movieland.data.datasource.movie.MovieDataSource
import com.movieland.data.source.network.model.detail.DetailMovieResponse
import com.movieland.data.source.network.model.movieNowPlaying.NowPlayingResponse
import com.movieland.data.source.network.services.MovielandApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DetailDataSourceImplTest {
    @MockK
    lateinit var service: MovielandApiService

    private lateinit var dataSource: DetailDataSource
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = DetailDataSourceImpl(service)
    }

    @Test
    fun detailMovies() {
        runTest {
            val mockResponse = mockk<DetailMovieResponse>(relaxed = true)
            coEvery { service.getMovieDetail(any()) } returns mockResponse
            val actualResult = dataSource.detailMovies(1)
            coVerify { service.getMovieDetail(any()) }
            assertEquals(mockResponse, actualResult)
        }
    }
}