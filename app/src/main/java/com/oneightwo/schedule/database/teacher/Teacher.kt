package com.oneightwo.schedule.database.teacher

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Teacher(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "teacher")
    val teacher: String?
)