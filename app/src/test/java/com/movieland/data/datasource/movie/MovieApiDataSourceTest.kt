package com.movieland.data.datasource.movie

import com.movieland.data.source.network.model.movieNowPlaying.NowPlayingResponse
import com.movieland.data.source.network.model.moviePopular.PopularMovieResponse
import com.movieland.data.source.network.model.movieTopRating.TopRatedResponse
import com.movieland.data.source.network.model.movieUpcoming.UpcomingMovieResponse
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

class MovieApiDataSourceTest {
    @MockK
    lateinit var service: MovielandApiService

    private lateinit var dataSource: MovieDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = MovieApiDataSource(service)
    }

    @Test
    fun getNowPlaying() {
        runTest {
            val mockResponse = mockk<NowPlayingResponse>(relaxed = true)
            coEvery { service.nowPlaying(any()) } returns mockResponse
            val actualResult = dataSource.getNowPlaying(1)
            coVerify { service.nowPlaying(any()) }
            assertEquals(mockResponse, actualResult)
        }
    }

    @Test
    fun getPopular() {
        runTest {
            val mockResponse = mockk<PopularMovieResponse>(relaxed = true)
            coEvery { service.popular(any()) } returns mockResponse
            val actualResult = dataSource.getPopular(1)
            coVerify { service.popular(any()) }
            assertEquals(mockResponse, actualResult)
        }
    }

    @Test
    fun getTopRating() {
        runTest {
            val mockResponse = mockk<TopRatedResponse>(relaxed = true)
            coEvery { service.topRated(any()) } returns mockResponse
            val actualResult = dataSource.getTopRating(1)
            coVerify { service.topRated(any()) }
            assertEquals(mockResponse, actualResult)
        }
    }

    @Test
    fun getUpcoming() {
        runTest {
            val mockResponse = mockk<UpcomingMovieResponse>(relaxed = true)
            coEvery { service.upcoming(any()) } returns mockResponse
            val actualResult = dataSource.getUpcoming(1)
            coVerify { service.upcoming(any()) }
            assertEquals(mockResponse, actualResult)
        }
    }
}