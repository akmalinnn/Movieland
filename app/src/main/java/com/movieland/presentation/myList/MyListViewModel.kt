package com.movieland.presentation.myList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.movieland.data.model.Movie
import com.movieland.data.repository.mylist.MyListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyListViewModel(
    private val repo : MyListRepository
) : ViewModel() {
    fun getAllFavorites() = repo.getAllMyList().asLiveData(Dispatchers.IO)

    fun addToFavorite(data: Movie) = repo.createMyList(data).asLiveData(Dispatchers.IO)


    fun checkMovieList(favoriteId: Int?) = repo.checkFavoriteById(favoriteId).asLiveData(Dispatchers.IO)

    fun removeFavoriteById(favoriteId: Int?) = repo.deleteMyListById(favoriteId).asLiveData(Dispatchers.IO)




}