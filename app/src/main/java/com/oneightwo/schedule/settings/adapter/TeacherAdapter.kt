package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.settings.base.BaseSettingAdapter

class TeacherAdapter(
    private val addPosition: (Int, Teacher) -> Unit,
    private val removePosition: (Int, Teacher) -> Unit,
    private val getPositions: () -> ArrayList<Int>,
    private val setLongClick: (Boolean) -> Unit,
    private val isLongClick: () -> Boolean
) : BaseSettingAdapter<Teacher>(addPosition, removePosition, getPositions, setLongClick, isLongClick) {

    override fun getText(item: Teacher): String {
        return item.teacher.toString()
    }
}