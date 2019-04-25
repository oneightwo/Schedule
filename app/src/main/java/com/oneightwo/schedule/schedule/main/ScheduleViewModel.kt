package com.oneightwo.schedule.schedule.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {

    private val weekFAB = MutableLiveData<Int>()

    fun getStateWeekFAB() = weekFAB

    fun changeStateWeekFAB() {
        if (weekFAB.value == 1) weekFAB.value = 2
        else weekFAB.value = 1
    }



}