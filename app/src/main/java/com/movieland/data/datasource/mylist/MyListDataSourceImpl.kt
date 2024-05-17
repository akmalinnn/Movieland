package com.movieland.data.datasource.mylist

import com.movieland.data.source.local.database.dao.MyListDao
import com.movieland.data.source.local.database.entity.MyListEntity
import kotlinx.coroutines.flow.Flow

class MyListDataSourceImpl(
    private val dao: MyListDao
) : MyListDataSource {
    override fun getAllMyList(): Flow<List<MyListEntity>> = dao.getAllMyList()

    override suspend fun insertMyList(mylist: MyListEntity): Long = dao.insertMylist(mylist)

    override suspend fun deleteMyList(mylist: MyListEntity): Int = dao.deleteFavorite(mylist)
    override suspend fun deleteMyListbyId(favoriteId: Int?): Int = dao.removeFavorite(favoriteId)

    override suspend fun deleteAll() = dao.deleteAll()
    override fun checkFavoriteById(movieId: Int?): Flow<List<MyListEntity>> = dao.checkFavoriteById(movieId)
//    override suspend fun removeFavoriteById(favoriteId: Int?): Int = dao.removeFavorite(favoriteId)
}