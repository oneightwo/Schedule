package com.oneightwo.schedule.settings.viewModel

import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.settings.base.BaseSettingViewModel
import com.oneightwo.schedule.tools.App

class TeacherViewModel : BaseSettingViewModel<Teacher>() {

    override val cls = Teacher::class.java
    override val data = App.db.teacherDao().getAllData()
}