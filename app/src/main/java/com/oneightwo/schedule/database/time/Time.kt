package com.oneightwo.schedule.database.time

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Time(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "first_time")
    val firstTime: String,
    @ColumnInfo(name = "second_time")
    val secondTime: String
)