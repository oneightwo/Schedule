package com.oneightwo.schedule.database.teacher

import androidx.room.Dao
import androidx.room.Query
import com.oneightwo.schedule.database.base.BaseDao

@Dao
abstract class TeacherDao : BaseDao<Teacher>{

    @Query("SELECT * FROM teacher")
    abstract fun getAll(): List<Teacher>

    @Query("SELECT * FROM teacher WHERE id IN (:teacherIds)")
    abstract fun loadAllByIds(teacherIds: IntArray): List<Teacher>

    @Query("SELECT * FROM teacher WHERE id = (:teacherId)")
    abstract fun loadById(teacherId: Int): Teacher


//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(subject: String): Subject
}