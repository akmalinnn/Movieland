package com.movieland.data.repository.movie

import app.cash.turbine.test
import com.catnip.kokomputer.utils.ResultWrapper
import com.movieland.data.datasource.movie.MovieDataSource
import com.movieland.data.source.network.model.movieNowPlaying.NowPlayingResponse
import com.movieland.data.source.network.model.movieNowPlaying.ResultNowPlayingResponse
import com.movieland.data.source.network.model.moviePopular.PopularMovieResponse
import com.movieland.data.source.network.model.moviePopular.ResultPopularMovieResponse
import com.movieland.data.source.network.model.movieTopRating.ResultTopRatedResponse
import com.movieland.data.source.network.model.movieTopRating.TopRatedResponse
import com.movieland.data.source.network.model.movieUpcoming.ResultUpcomingMovieResponse
import com.movieland.data.source.network.model.movieUpcoming.UpcomingMovieResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {
    @MockK
    lateinit var ds: MovieDataSource
    private lateinit var repo: MovieRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = MovieRepositoryImpl(ds)
    }

    @Test
    fun `get nowPlaying loading`() {
        val c1 = ResultNowPlayingResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val c2 = ResultNowPlayingResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<NowPlayingResponse>()
        every { mockResponse.results } returns mockListCategory
        runTest {
            coEvery { ds.getNowPlaying(any()) } returns mockResponse
            repo.getNowPlaying(1).map {
                delay(100)
                it
            }.test {
                delay(110)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify { ds.getNowPlaying(any()) }
            }
        }
    }

    @Test
    fun `get nowPlaying success`() {
        val c1 = ResultNowPlayingResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val c2 = ResultNowPlayingResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<NowPlayingResponse>()
        every { mockResponse.results } returns mockListCategory
        runTest {
            coEvery { ds.getNowPlaying(any()) } returns mockResponse
            repo.getNowPlaying(1).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.getNowPlaying(any()) }
            }
        }
    }

    @Test
    fun `get popular success`() {
        val c1 = ResultPopularMovieResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val c2 = ResultPopularMovieResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<PopularMovieResponse>()
        every { mockResponse.results } returns mockListCategory
        runTest {
            coEvery { ds.getPopular(any()) } returns mockResponse
            repo.getPopular(1).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.getPopular(any()) }
            }
        }
    }

    @Test
    fun `get topRated success`() {
        val c1 = ResultTopRatedResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val c2 = ResultTopRatedResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<TopRatedResponse>()
        every { mockResponse.results } returns mockListCategory
        runTest {
            coEvery { ds.getTopRating(any()) } returns mockResponse
            repo.getTopRating(1).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.getTopRating(any()) }
            }
        }
    }

    @Test
    fun `get upcoming success`() {
        val c1 = ResultUpcomingMovieResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val c2 = ResultUpcomingMovieResponse(
            adult = false,
            backdropPath = "path/to/backdrop",
            genreIds = listOf(1, 2, 3),
            id = 123,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 8.7,
            posterPath = "awfawf",
            releaseDate = "2024-01-01",
            title = "psu",
            video = false,
            voteAverage = 7.5,
            voteCount = 100
        )
        val mockListCategory = listOf(c1, c2)
        val mockResponse = mockk<UpcomingMovieResponse>()
        every { mockResponse.results } returns mockListCategory
        runTest {
            coEvery { ds.getUpcoming(any()) } returns mockResponse
            repo.getUpcoming(1).map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.getUpcoming(any()) }
            }
        }
    }
}