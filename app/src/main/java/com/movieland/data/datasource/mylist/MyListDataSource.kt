package com.movieland.data.datasource.mylist

import com.movieland.data.source.local.database.entity.MyListEntity
import kotlinx.coroutines.flow.Flow

interface MyListDataSource {
    fun getAllMyList(): Flow<List<MyListEntity>>

    fun checkFavoriteById(movieId: Int?): Flow<List<MyListEntity>>

    suspend fun insertMyList(mylist: MyListEntity): Long

    suspend fun deleteMyList(mylist: MyListEntity): Int
    suspend fun deleteMyListbyId(favoriteId: Int?): Int

    suspend fun deleteAll()
}