package com.oneightwo.schedule.database.time

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimeDao {

    @Query("SELECT * FROM time")
    fun getAll(): List<Time>

    @Query("SELECT * FROM time WHERE id IN (:timeIds)")
    fun loadAllByIds(timeIds: IntArray): List<Time>

    @Query("SELECT * FROM time WHERE id = (:timeId)")
    fun loadById(timeId: Int): Time


//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(subject: String): Subject

    @Insert
    fun insertAll(vararg times: Time)

    @Delete
    fun delete(times: Time)
}