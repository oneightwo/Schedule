package com.oneightwo.schedule.schedule.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {

    private val weekFAB = MutableLiveData<Boolean>()
    var week = 1

    init {
        weekFAB.value = true
    }

    fun getStateWeekFAB() = weekFAB

    fun changeStateWeekFAB() {
        weekFAB.value = !weekFAB.value!!
    }



}