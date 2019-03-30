package com.oneightwo.schedule.database.time

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimeEnd(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "time")
    val time: String
)