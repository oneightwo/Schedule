package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.settings.base.BaseSettingAdapter

class CabinetAdapter(
    private val addPosition: (Int, Cabinet) -> Unit,
    private val removePosition: (Int, Cabinet) -> Unit,
    private val getPositions: () -> ArrayList<Int>,
    private val setLongClick: (Boolean) -> Unit,
    private val isLongClick: () -> Boolean
): BaseSettingAdapter<Cabinet>(addPosition, removePosition, getPositions, setLongClick, isLongClick) {

    override fun getText(item: Cabinet): String {
        return item.cabinet.toString()
    }
}