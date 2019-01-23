package com.oneightwo.schedule.settings

import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingsViewModel : ViewModel() {

    fun getAllData(index: Int, callback: (List<String>) -> Unit) {
        val o = Observable.fromCallable {
            with(App.db) {
                when (index) {
                    0 -> subjectDao().getAll().map { it.subject!! }
                    1 -> timeDao().getAll().map { it.time!! }
                    2 -> App.db.cabinetDao().getAll().map { it.cabinet!! }
                    3 -> App.db.teacherDao().getAll().map { it.teacher!! }
                    else -> arrayListOf()
                }
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { subject ->
                callback(subject)
            }
    }

    fun add(data: String, index: Int) {
        val subscription = Observable.fromCallable {
            with(App.db) {
                when (index) {
                    0 -> subjectDao().insertAll(Subject(0, data))
                    1 -> timeDao().insertAll(Time(0, data))
                    2 -> cabinetDao().insertAll(Cabinet(0, data))
                    3 -> teacherDao().insertAll(Teacher(0, data))
                }
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
    }

   // fun getItemData(position: Int) = App.db.subjectDao().loadById(position)

}

