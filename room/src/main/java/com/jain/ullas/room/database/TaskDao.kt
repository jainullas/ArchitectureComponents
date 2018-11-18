package com.jain.ullas.room.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task): Long

    @Insert
    fun insertAll(vararg task: Task)

    @Query("SELECT * FROM Task")
    fun getAll() : List<Task>
}