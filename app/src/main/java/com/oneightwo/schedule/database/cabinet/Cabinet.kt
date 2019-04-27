package com.oneightwo.schedule.database.cabinet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cabinet(
    @PrimaryKey
    @ColumnInfo(name = "cabinet")
    val cabinet: String
)