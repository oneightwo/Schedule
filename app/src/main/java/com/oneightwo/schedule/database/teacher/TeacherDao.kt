package com.oneightwo.schedule.database.teacher

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TeacherDao {
    @Query("SELECT * FROM teacher")
    fun getAll(): List<Teacher>

    @Query("SELECT * FROM teacher WHERE id IN (:teacherIds)")
    fun loadAllByIds(teacherIds: IntArray): List<Teacher>

    @Query("SELECT * FROM teacher WHERE id = (:teacherId)")
    fun loadById(teacherId: Int): Teacher


//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(subject: String): Subject

    @Insert
    fun insertAll(vararg teachers: Teacher)

    @Delete
    fun delete(teachers: Teacher)
}