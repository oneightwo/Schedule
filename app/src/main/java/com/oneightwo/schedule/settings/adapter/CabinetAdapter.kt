package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.settings.base.BaseSettingAdapter
import com.oneightwo.schedule.settings.base.FormDataSetting
class CabinetAdapter(
    addDataDeletions: (Int, Cabinet) -> Unit,
    removeDataDeletions: (Int, Cabinet) -> Unit,
    listDataDeletions: () -> ArrayList<FormDataSetting<Cabinet>>,
    changeStateFAB: (Boolean) -> Unit,
    stateFAB: () -> Boolean
) : BaseSettingAdapter<Cabinet>(addDataDeletions, removeDataDeletions, listDataDeletions, changeStateFAB, stateFAB) {

    override fun getText(item: Cabinet): String {
        return item.cabinet.toString()
    }
}