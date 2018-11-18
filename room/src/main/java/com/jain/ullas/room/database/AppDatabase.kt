package com.jain.ullas.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        version = 1, //needs to be incremented for every schema change in DB, otherwise it gives runtime exception
        entities = [Task::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): TaskDao

    //Making it singleton for simpler implementation
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE
                        ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "app-database").build()

        }
    }

}