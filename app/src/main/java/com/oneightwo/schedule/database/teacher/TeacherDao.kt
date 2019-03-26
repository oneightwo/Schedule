package com.oneightwo.schedule.database.teacher

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class TeacherDao : BaseDao<Teacher>{

    @Query("SELECT * FROM teacher")
    abstract fun getAllData(): LiveData<List<Teacher>>

}