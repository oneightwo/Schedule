package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.settings.base.BaseSettingAdapter

class TimeAdapter(
    private val addPosition: (Int, Time) -> Unit,
    private val removePosition: (Int, Time) -> Unit,
    private val getPositions: () -> ArrayList<Int>,
    private val setLongClick: (Boolean) -> Unit,
    private val isLongClick: () -> Boolean
): BaseSettingAdapter<Time>(addPosition, removePosition, getPositions, setLongClick, isLongClick) {

    override fun getText(item: Time): String {
        return item.time.toString()
    }
}