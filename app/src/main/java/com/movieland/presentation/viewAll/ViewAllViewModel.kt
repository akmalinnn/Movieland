package com.movieland.presentation.viewAll

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.movieland.data.model.Movie
import com.movieland.data.repository.view.ViewAllPagingRepository
import kotlinx.coroutines.flow.Flow

class ViewAllViewModel (private val extras: Bundle?, private val repository: ViewAllPagingRepository) : ViewModel() {
    val header = extras?.getString(ViewAllActivity.HEADER)
    fun topRating(): Flow<PagingData<Movie>> = repository.getTopRatedList().cachedIn(viewModelScope)

    fun nowPlaying(): Flow<PagingData<Movie>> = repository.getNowPlayingList().cachedIn(viewModelScope)

    fun getPopularMovie(): Flow<PagingData<Movie>> = repository.getPopularList().cachedIn(viewModelScope)

    fun upcoming(): Flow<PagingData<Movie>> = repository.getUpComingList().cachedIn(viewModelScope)
}