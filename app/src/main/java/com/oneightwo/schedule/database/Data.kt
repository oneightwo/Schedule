package com.oneightwo.schedule.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "data")
    var data: String
)