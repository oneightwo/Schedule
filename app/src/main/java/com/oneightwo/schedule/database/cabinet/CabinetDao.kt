package com.oneightwo.schedule.database.cabinet

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class CabinetDao : BaseDao<Cabinet> {

    @Query("SELECT * FROM Cabinet")
    abstract fun getAllData(): LiveData<List<Cabinet>>

}