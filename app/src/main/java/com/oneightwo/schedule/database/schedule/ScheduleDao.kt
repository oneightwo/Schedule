package com.oneightwo.schedule.database.schedule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class ScheduleDao: BaseDao<Schedule> {

    @Query("SELECT * FROM schedule")
    abstract fun getAllData(): LiveData<List<Schedule>>

}