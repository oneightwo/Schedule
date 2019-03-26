package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.settings.base.BaseSettingAdapter
import com.oneightwo.schedule.settings.base.FormDataSetting

class SubjectAdapter(
    addDataDeletions: (Int, Subject) -> Unit,
    removeDataDeletions: (Int, Subject) -> Unit,
    listDataDeletions: () -> ArrayList<FormDataSetting<Subject>>,
    changeStateFAB: (Boolean) -> Unit,
    stateFAB: () -> Boolean
) : BaseSettingAdapter<Subject>(addDataDeletions, removeDataDeletions, listDataDeletions, changeStateFAB, stateFAB) {

    override fun getText(item: Subject): String {
        return item.subject
    }
}