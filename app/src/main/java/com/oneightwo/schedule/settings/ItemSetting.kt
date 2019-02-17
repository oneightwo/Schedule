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

abstract class ItemSetting<T> : ViewModel() {

    private val t: T? = null

    fun getAllData(index: Int, callback: (List<*>) -> Unit) {
        val o = Observable.fromCallable {
            with(App.db) {
                when (t) {
                    is Subject -> subjectDao().getAll()
                    is Time -> timeDao().getAll()
                    is Cabinet -> cabinetDao().getAll()
                    is Teacher -> teacherDao().getAll()
                    else -> listOf<T>()
                }
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                callback(data as List<*>)
            }
    }

}