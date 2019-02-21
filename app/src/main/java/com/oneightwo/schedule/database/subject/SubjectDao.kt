package com.oneightwo.schedule.database.subject

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubjectDao{

    @Query("SELECT * FROM subject")
    fun getAll(): List<Subject>

    @Query("SELECT * FROM subject WHERE id IN (:subjectIds)")
    fun loadAllByIds(subjectIds: IntArray): List<Subject>

    @Query("SELECT * FROM subject WHERE id = (:subjectId)")
    fun loadById(subjectId: Int): Subject


//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(subject: String): Subject

//    @Query("DELETE FROM subject WHERE id = (:subjectIds)")
//    fun deleteMultiple(vararg subjectIds: Subject)

    @Insert
    fun insertAll(vararg subjects: Subject)

    @Delete
    fun delete(subject: Subject)
}