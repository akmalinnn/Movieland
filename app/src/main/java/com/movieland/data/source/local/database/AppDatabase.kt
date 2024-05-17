package com.movieland.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.movieland.data.source.local.database.dao.MyListDao
import com.movieland.data.source.local.database.entity.MyListEntity

@Database(
    entities = [MyListEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): MyListDao

    companion object {
        private const val DB_NAME = "Movieland.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME,
            ).fallbackToDestructiveMigration().build()
        }
    }
}
