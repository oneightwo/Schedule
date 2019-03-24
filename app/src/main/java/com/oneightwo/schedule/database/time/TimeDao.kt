package com.oneightwo.schedule.database.time

import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class TimeDao : BaseDao<Time>{

    @Query("SELECT * FROM time")
    abstract fun getAll(): List<Time>

    @Query("SELECT * FROM time WHERE id IN (:timeIds)")
    abstract fun loadAllByIds(timeIds: IntArray): List<Time>

    @Query("SELECT * FROM time WHERE id = (:timeId)")
    abstract fun loadById(timeId: Int): Time


//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(subject: String): Subject
}