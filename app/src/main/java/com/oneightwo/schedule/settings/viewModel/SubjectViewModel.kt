package com.oneightwo.schedule.settings.viewModel

import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.settings.base.BaseSettingViewModel
import com.oneightwo.schedule.tools.App

class SubjectViewModel : BaseSettingViewModel<Subject>() {

    override val cls = Subject::class.java
    override val data = App.db.subjectDao().getAllData()

}