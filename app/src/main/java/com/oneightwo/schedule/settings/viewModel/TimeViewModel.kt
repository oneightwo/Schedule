package com.oneightwo.schedule.settings.viewModel

import com.oneightwo.schedule.database.time.TimeStart
import com.oneightwo.schedule.settings.base.BaseSettingViewModel
import com.oneightwo.schedule.tools.App

class TimeViewModel : BaseSettingViewModel<TimeStart>() {

    override val cls = TimeStart::class.java
    override val data = App.db.timeStartDao().getAllData()
}