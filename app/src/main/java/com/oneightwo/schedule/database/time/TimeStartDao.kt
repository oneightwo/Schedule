package com.oneightwo.schedule.database.time

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class TimeStartDao : BaseDao<TimeStart>{

    @Query("SELECT * FROM TimeStart")
    abstract fun getAllData(): LiveData<List<TimeStart>>

}