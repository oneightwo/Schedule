package com.oneightwo.schedule.database.subject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class SubjectDao : BaseDao<Subject>{

    @Query("SELECT * FROM Subject")
    abstract fun getAllData() : LiveData<List<Subject>>
}