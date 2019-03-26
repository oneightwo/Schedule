package com.oneightwo.schedule.settings.viewModel

import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.settings.base.BaseSettingViewModel
import com.oneightwo.schedule.tools.App

class TimeViewModel : BaseSettingViewModel<Time>() {

    override val cls = Time::class.java
    override val data = App.db.timeDao().getAllData()
}