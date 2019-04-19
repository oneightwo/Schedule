package com.oneightwo.schedule.database.time

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Time(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "number")
    val number: Int
)