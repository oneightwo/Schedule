package com.oneightwo.schedule.settings

import com.oneightwo.schedule.database.base.BaseDao

interface Helper<T>{

    fun getDao(): BaseDao<T>

    fun add(data: T)

    fun delete(data: T)

    fun update(data: T)

}