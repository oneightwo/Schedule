package com.oneightwo.schedule.database.time

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class TimeEndDao : BaseDao<TimeEnd>{

    @Query("SELECT * FROM TimeEnd")
    abstract fun getAllData(): LiveData<List<TimeEnd>>

}