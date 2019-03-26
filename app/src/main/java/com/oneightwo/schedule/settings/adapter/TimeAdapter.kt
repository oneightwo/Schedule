package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.settings.base.BaseSettingAdapter
import com.oneightwo.schedule.settings.base.FormDataSetting

class TimeAdapter(
    addDataDeletions: (Int, Time) -> Unit,
    removeDataDeletions: (Int, Time) -> Unit,
    listDataDeletions: () -> ArrayList<FormDataSetting<Time>>,
    changeStateFAB: (Boolean) -> Unit,
    stateFAB: () -> Boolean
): BaseSettingAdapter<Time>(addDataDeletions, removeDataDeletions, listDataDeletions, changeStateFAB, stateFAB) {

    override fun getText(item: Time): String {
        return "${item.firstTime} - ${item.secondTime}"
    }
}