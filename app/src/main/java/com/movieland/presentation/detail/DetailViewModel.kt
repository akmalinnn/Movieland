package com.movieland.presentation.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.catnip.kokomputer.utils.ResultWrapper
import com.movieland.data.model.Movie
import com.movieland.data.model.MovieDetail
import com.movieland.data.repository.detail.DetailMovieRepository
import com.movieland.data.repository.movie.MovieRepository
import com.movieland.data.repository.mylist.MyListRepository
import com.movieland.data.source.local.database.entity.MyListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class DetailViewModel(
    private val extras: Bundle?,
    private val detailMovieRepository : DetailMovieRepository,
    private val movieRepository: MovieRepository,
    private val listRepository: MyListRepository
) : ViewModel() {

    val movie = extras?.getParcelable<Movie>(DetailFragment.EXTRAS_MOVIE)

    fun getDetail(id: Int): LiveData<ResultWrapper<MovieDetail>> {
        return detailMovieRepository.detailMovies(id).asLiveData(Dispatchers.IO)
    }

//    fun addToMyList(movie: Movie) {
//        viewModelScope.launch {
//            try {
//                // Call your repository method to add the movie to the user's list
//                movieRepository.addToMyList(movie)
//            } catch (e: Exception) {
//                // Handle exceptions here
//            }
//        }
//    }

    fun removeFavorite(item: Movie) {
        viewModelScope.launch(Dispatchers.IO) { listRepository.deleteMyList(item).collect() }
    }

    fun addToFavorite(app: MyListEntity?): LiveData<ResultWrapper<Boolean>> {
        return movie?.let {
            listRepository.createMyList(it).asLiveData(Dispatchers.IO)

        } ?: liveData { emit(ResultWrapper.Error(IllegalStateException("not found"))) }
    }


}
