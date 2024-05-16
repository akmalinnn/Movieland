package com.movieland.data.datasource.mylist

import com.movieland.data.source.local.database.dao.MyListDao
import com.movieland.data.source.local.database.entity.MyListEntity
import kotlinx.coroutines.flow.Flow

class MyListDataSourceImpl(
    private val dao: MyListDao
) : MyListDataSource {
    override fun getAllMyList(): Flow<List<MyListEntity>> = dao.getAllMyList()

    override suspend fun insertMyList(mylist: MyListEntity): Long = dao.insertMylist(mylist)

    override suspend fun deleteMyList(mylist: MyListEntity): Int = dao.deleteMyList(mylist)

    override suspend fun deleteAll() = dao.deleteAll()
}