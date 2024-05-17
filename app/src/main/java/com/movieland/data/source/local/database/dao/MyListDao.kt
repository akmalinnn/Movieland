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

    @Delete()
    suspend fun deleteMyList(mylist: MyListEntity): Int

    @Query("DELETE FROM MYLIST")
    suspend fun deleteAll()
}
