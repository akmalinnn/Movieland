package com.movieland.data.datasource.mylist

import com.movieland.data.source.local.database.entity.MyListEntity
import kotlinx.coroutines.flow.Flow

interface MyListDataSource {
    fun getAllMyList(): Flow<List<MyListEntity>>

    suspend fun insertMyList(mylist: MyListEntity): Long

    suspend fun deleteMyList(mylist: MyListEntity): Int

    suspend fun deleteAll()
}