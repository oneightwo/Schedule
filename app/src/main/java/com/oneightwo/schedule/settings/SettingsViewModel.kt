package com.oneightwo.schedule.settings

import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.tools.App

class SettingsViewModel : ViewModel() {

    fun add(data: String, index: Int) {
        with(App.db) {
            when (index) {
                0 -> subjectDao().insertAll(Subject(0, data))
                1 -> timeDao().insertAll(Time(0, data))
                2 -> cabinetDao().insertAll(Cabinet(0, data))
                3 -> teacherDao().insertAll(Teacher(0, data))

            }
        }
    }

    fun getItemData(position: Int) = App.db.subjectDao().loadById(position)

    fun getAllData(index: Int) = when (index) {
        0 -> App.db.subjectDao().getAll().map { it.subject!! }
        1 -> App.db.timeDao().getAll().map { it.time!! }
        2 -> App.db.cabinetDao().getAll().map { it.cabinet!! }
        3 -> App.db.teacherDao().getAll().map { it.teacher!! }
        else -> arrayListOf()
    }
}

