package com.oneightwo.schedule.settings.viewModel

import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.settings.base.BaseSettingViewModel
import com.oneightwo.schedule.tools.App

class CabinetViewModel : BaseSettingViewModel<Cabinet>() {

    override val cls = Cabinet::class.java
    override val data = App.db.cabinetDao().getAllData()

}

