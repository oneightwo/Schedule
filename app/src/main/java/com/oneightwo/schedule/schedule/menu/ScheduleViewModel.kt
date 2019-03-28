package com.oneightwo.schedule.schedule.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {

    private val addFAB = MutableLiveData<Boolean>()
    private val weekFAB = MutableLiveData<Boolean>()

    init {
        weekFAB.value = true
        addFAB.value = false
    }

    fun getStateWeekFAB() = weekFAB

    fun getStateAddFAB() = addFAB

    fun changeStateWeekFAB() {
        weekFAB.value = !weekFAB.value!!
    }

    fun changeStateAddFAB() {
        addFAB.value = !addFAB.value!!
    }



}