package com.oneightwo.schedule.schedule.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {

    private val settingFAB = MutableLiveData<Boolean>()
    private val mainFAB = MutableLiveData<Boolean>()

    init {
        mainFAB.value = true
        settingFAB.value = false
    }

    fun getStateMainFAB() = mainFAB

    fun getStateSettingFAB() = settingFAB

    fun changeStateMainFAB() {
        mainFAB.value = !mainFAB.value!!
    }

    fun changeStateSettingFAB() {
        settingFAB.value = !settingFAB.value!!
    }


}