package com.movieland.presentation.myList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.movieland.data.repository.mylist.MyListRepository
import kotlinx.coroutines.Dispatchers

class MyListViewModel(
    private val repo : MyListRepository
) : ViewModel() {
    fun getAllFavorites() = repo.getAllMyList().asLiveData(Dispatchers.IO)
}