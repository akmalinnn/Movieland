package com.movieland.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movieland.data.repository.MovieNowPlayingRepository
import com.movieland.data.repository.MoviePopularRepository
import com.movieland.data.repository.MovieTopRatedRepository
import com.movieland.data.repository.MovieUpComingRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val movieNowPlayingRepository: MovieNowPlayingRepository,
    private val moviePopularRepository: MoviePopularRepository,
    private val movieTopRatedRepository: MovieTopRatedRepository,
    private val movieUpComingRepository: MovieUpComingRepository,
) : ViewModel() {
    fun getMoviesNowPlaying() = movieNowPlayingRepository.getMoviesNowPlaying().asLiveData(
        Dispatchers.IO)

    fun getMoviesPopular() = moviePopularRepository.getPopularPlaying().asLiveData(Dispatchers.IO)

    fun getMoviesTopRated() = movieTopRatedRepository.getTopRatedPlaying().asLiveData(Dispatchers.IO)

    fun getMoviesUpComing() = movieUpComingRepository.getUpComingPlaying().asLiveData(Dispatchers.IO)
}