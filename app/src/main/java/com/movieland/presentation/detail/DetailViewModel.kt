package com.movieland.presentation.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.kokomputer.utils.ResultWrapper
import com.movieland.data.model.Movie
import com.movieland.data.model.MovieDetail
import com.movieland.data.repository.detail.DetailMovieRepository
import com.movieland.data.repository.movie.MovieRepository
import kotlinx.coroutines.Dispatchers

class DetailViewModel(
    private val extras: Bundle?,
    private val detailMovieRepository : DetailMovieRepository
) : ViewModel() {

    val movie = extras?.getParcelable<Movie>(DetailFragment.EXTRAS_MOVIE)

    fun getDetail(id: Int): LiveData<ResultWrapper<MovieDetail>> {
        return detailMovieRepository.detailMovies(id).asLiveData(Dispatchers.IO)
    }

}
