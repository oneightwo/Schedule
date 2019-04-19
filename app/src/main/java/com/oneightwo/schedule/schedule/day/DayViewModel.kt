package com.oneightwo.schedule.schedule.day

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.tools.App

class DayViewModel : ViewModel() {

    var numberPage: Int? = null
    val data = arrayListOf<Schedule>()

    var storage = listOf<Schedule>()

    val scheduleDao = App.db.scheduleDao()
    val day: LiveData<List<Schedule>> = App.db.scheduleDao().getAllData()

    fun get() {

    }

}