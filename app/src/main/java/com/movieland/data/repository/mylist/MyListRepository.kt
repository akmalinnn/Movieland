package com.movieland.data.repository.mylist

import com.catnip.kokomputer.utils.ResultWrapper
import com.movieland.data.model.Movie
import com.movieland.data.model.MovieDetail
import com.movieland.data.model.MyList
import com.movieland.data.source.local.database.entity.MyListEntity
import kotlinx.coroutines.flow.Flow

interface MyListRepository {
    fun getAllMyList(): Flow<ResultWrapper<Pair<List<Movie>, Double>>>
//    fun createMyList(item: MovieDetail): Flow<ResultWrapper<Boolean>>

    fun createMyList(item: Movie): Flow<ResultWrapper<Boolean>>
    fun checkFavoriteById(movieId: Int?): Flow<List<MyListEntity>>
    fun deleteMyList(item: Movie): Flow<ResultWrapper<Boolean>>
    fun deleteMyListById(movieId: Int?): Flow<ResultWrapper<Boolean>>
    fun deleteAll(): Flow<ResultWrapper<Boolean>>
}