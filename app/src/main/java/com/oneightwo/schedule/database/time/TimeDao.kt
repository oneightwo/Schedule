package com.oneightwo.schedule.database.time

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class TimeDao : BaseDao<Time>{

    @Query("SELECT * FROM Time")
    abstract fun getAllData(): LiveData<List<Time>>

}