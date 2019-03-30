package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.time.TimeStart
import com.oneightwo.schedule.settings.base.BaseSettingAdapter
import com.oneightwo.schedule.settings.base.FormDataSetting

class TimeAdapter(
    addDataDeletions: (Int, TimeStart) -> Unit,
    removeDataDeletions: (Int, TimeStart) -> Unit,
    listDataDeletions: () -> ArrayList<FormDataSetting<TimeStart>>,
    changeStateFAB: (Boolean) -> Unit,
    stateFAB: () -> Boolean
): BaseSettingAdapter<TimeStart>(addDataDeletions, removeDataDeletions, listDataDeletions, changeStateFAB, stateFAB) {

    override fun getText(item: TimeStart): String {
        return "${item.time}"
    }
}