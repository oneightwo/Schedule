package com.oneightwo.schedule.database.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "topic")
    val topic: String,
    @ColumnInfo(name = "subject")
    val subject: String,
    @ColumnInfo(name = "note")
    val note: String,
    @ColumnInfo(name = "time")
    val time: String?,
    @ColumnInfo(name = "notification")
    val notification: Boolean
)