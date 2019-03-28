package com.oneightwo.schedule.menu_аdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.tools.App

class MenuAddViewModel: ViewModel() {

    private val liveData: LiveData<List<Schedule>> = App.db.scheduleDao().getAllData()



}