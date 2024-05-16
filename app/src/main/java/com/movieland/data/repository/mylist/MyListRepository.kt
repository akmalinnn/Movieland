package com.movieland.data.repository.mylist

import com.catnip.kokomputer.utils.ResultWrapper
import com.movieland.data.model.MovieDetail
import com.movieland.data.model.MyList
import kotlinx.coroutines.flow.Flow

interface MyListRepository {
    fun getAllMyList(): Flow<ResultWrapper<Pair<List<MyList>, Double>>>
    fun createMyList(item: MovieDetail): Flow<ResultWrapper<Boolean>>
    fun deleteMyList(item: MyList): Flow<ResultWrapper<Boolean>>
    fun deleteAll(): Flow<ResultWrapper<Boolean>>
}