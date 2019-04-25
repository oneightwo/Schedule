package com.oneightwo.schedule.database.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class NotesDao : BaseDao<Notes> {

    @Query("SELECT * FROM Notes")
    abstract fun getAllData() : LiveData<List<Notes>>
}