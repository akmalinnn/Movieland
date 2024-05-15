package com.movieland.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movieland.data.repository.MovieNowPlayingRepository
import com.movieland.data.repository.MoviePopularRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val movieNowPlayingRepository: MovieNowPlayingRepository,
    private val moviePopularRepository: MoviePopularRepository,

) : ViewModel() {
    fun getMoviesNowPlaying() = movieNowPlayingRepository.getMoviesNowPlaying().asLiveData(
        Dispatchers.IO)

    fun getMoviesPopular() = moviePopularRepository.getPopularPlaying().asLiveData(Dispatchers.IO)
}