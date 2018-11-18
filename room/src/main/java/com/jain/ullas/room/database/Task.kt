package com.jain.ullas.room.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Task(@PrimaryKey(autoGenerate = true) // autogen - only works with INT and LONG
                var id : Int = 0,
                var title : String = "",
                var completed : Boolean = false)