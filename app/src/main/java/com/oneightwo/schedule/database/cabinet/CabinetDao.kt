package com.oneightwo.schedule.database.cabinet

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CabinetDao {
    @Query("SELECT * FROM cabinet")
    fun getAll(): List<Cabinet>

    @Query("SELECT * FROM cabinet WHERE id IN (:cabinetIds)")
    fun loadAllByIds(cabinetIds: IntArray): List<Cabinet>

    @Query("SELECT * FROM cabinet WHERE id = (:cabinetId)")
    fun loadById(cabinetId: Int): Cabinet


//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(subject: String): Subject

    @Insert
    fun insertAll(vararg cabinets: Cabinet)

    @Delete
    fun delete(cabinets: Cabinet)
}