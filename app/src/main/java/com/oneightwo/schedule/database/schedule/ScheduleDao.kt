package com.oneightwo.schedule.database.schedule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
interface ScheduleDao: BaseDao<Schedule> {

    @Query("SELECT * FROM schedule")
    fun getAll(): LiveData<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE id IN (:scheduleIds)")
    fun loadAllByIds(scheduleIds: IntArray): List<Schedule>

    @Query("SELECT * FROM schedule WHERE id = (:scheduleId)")
    fun loadById(scheduleId: Int): Schedule


//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(subject: String): Subject

//    @Query("DELETE FROM subject WHERE id = (:subjectIds)")
//    fun deleteMultiple(vararg subjectIds: Subject)

    @Insert
    fun insertAll(vararg schedules: Schedule)
}