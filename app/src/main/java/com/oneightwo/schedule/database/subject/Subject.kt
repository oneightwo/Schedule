package com.oneightwo.schedule.database.subject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "subject")
    var subject: String?
)