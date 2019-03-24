package com.oneightwo.schedule.database.base

import androidx.room.Delete
import androidx.room.Update

interface BaseDao<T> {
//
//    @Insert
//    fun insert(obj: T)

    fun insert(vararg obj: T)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}