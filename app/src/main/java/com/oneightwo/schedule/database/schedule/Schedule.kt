package com.oneightwo.schedule.database.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "week")
    val week: Int,
    @ColumnInfo(name = "day")
    val day: Int,
    @ColumnInfo(name = "first_time")
    val firstTime: String,
    @ColumnInfo(name = "second_time")
    val secondTime: String,
    @ColumnInfo(name = "subject")
    val subject: String,
    @ColumnInfo(name = "cabinet")
    val cabinet: String?,
    @ColumnInfo(name = "teacher")
    val teacher: String?,
    @ColumnInfo(name = "type")
    val type: String?
)