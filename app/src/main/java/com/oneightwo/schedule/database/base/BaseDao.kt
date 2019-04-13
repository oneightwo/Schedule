package com.oneightwo.schedule.database.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    //
//    @Insert
//    fun insert(obj: T)
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg obj: T)

    @Update
    fun update(obj: T)

    @Update
    fun update(vararg obj: T)

    @Delete
    fun delete(obj: T)

}