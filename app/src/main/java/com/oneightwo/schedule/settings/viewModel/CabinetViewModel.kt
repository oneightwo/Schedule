package com.oneightwo.schedule.settings.viewModel

import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.cabinet.CabinetDao
import com.oneightwo.schedule.settings.base.BaseViewModel
import com.oneightwo.schedule.tools.App

class CabinetViewModel : BaseViewModel<Cabinet, CabinetDao>() {


    override fun getDao(): CabinetDao = App.db.cabinetDao()
    override val data = App.db.cabinetDao().getAllData()

}

