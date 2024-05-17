package com.movieland.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.movieland.data.source.local.database.entity.MyListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyListDao {
    @Query("SELECT * FROM MYLIST")
    fun getAllMyList(): Flow<List<MyListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMylist(mylist: MyListEntity): Long

    @Update()
    suspend fun updateMyList(mylist: MyListEntity): Int

    @Query("SELECT * FROM MYLIST WHERE id = :movieId ")
    fun checkFavoriteById(movieId: Int?): Flow<List<MyListEntity>>

    @Query("DELETE FROM MYLIST WHERE id = :movieId")
    suspend fun removeFavorite(movieId: Int?): Int

    @Delete
    suspend fun deleteFavorite(favorite: MyListEntity): Int


    @Query("DELETE FROM MYLIST")
    suspend fun deleteAll()
}
