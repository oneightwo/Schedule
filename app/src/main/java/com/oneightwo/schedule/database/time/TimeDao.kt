package com.oneightwo.schedule.database.time

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class TimeDao : BaseDao<Time>{

    @Query("SELECT * FROM time")
    abstract fun getAllData(): LiveData<List<Time>>

}