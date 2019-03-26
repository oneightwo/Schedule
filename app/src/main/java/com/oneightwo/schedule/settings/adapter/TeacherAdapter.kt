package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.settings.base.BaseSettingAdapter
import com.oneightwo.schedule.settings.base.FormDataSetting

class TeacherAdapter(
    addDataDeletions: (Int, Teacher) -> Unit,
    removeDataDeletions: (Int, Teacher) -> Unit,
    listDataDeletions: () -> ArrayList<FormDataSetting<Teacher>>,
    changeStateFAB: (Boolean) -> Unit,
    stateFAB: () -> Boolean
) : BaseSettingAdapter<Teacher>(addDataDeletions, removeDataDeletions, listDataDeletions, changeStateFAB, stateFAB) {

    override fun getText(item: Teacher): String {
        return item.teacher
    }

}