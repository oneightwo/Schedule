package com.oneightwo.schedule.settings.base

import androidx.lifecycle.ViewModel
import java.util.*

abstract class BaseSettingViewModel<T> : ViewModel() {

    private val dataV: ArrayList<T>? = null

    fun addV(data: List<T>) {
        dataV?.addAll(data)
    }

    fun addV(data: T) {
        dataV?.add(data)
    }

    fun getAllV() = dataV

    fun clearV() {
        dataV?.clear()
    }


    abstract fun deleteData()

    abstract fun addData(data: T)

    abstract fun addAllData()

    abstract fun loadAllData(callback: (List<T>) -> Unit)

    abstract fun getAllData(callback: (List<T>) -> Unit)

}