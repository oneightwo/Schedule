package com.oneightwo.schedule.settings.adapter

import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.settings.base.BaseSettingAdapter

class SubjectAdapter(
    private val deleteData: (Subject) -> Unit
): BaseSettingAdapter<Subject>(deleteData) {

    override fun getText(item: Subject): String {
        return item.subject.toString()
    }
}