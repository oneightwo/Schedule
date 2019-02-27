package com.oneightwo.schedule.schedule

import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ScheduleViewModel : ViewModel() {

    var week = 1
    var isSetting = false
    private var time = arrayListOf<Time>()
    private var subject = arrayListOf<Subject>()
    private var teacher = arrayListOf<Teacher>()
    private var cabinet = arrayListOf<Cabinet>()

    fun getData(
        callback1: (List<Time>) -> Unit,
        callback2: (List<Subject>) -> Unit,
        callback3: (List<Teacher>) -> Unit,
        callback4: (List<Cabinet>) -> Unit
    ) {
        if (time.isEmpty()) {
            getAllTime {
                callback1(it)
            }
        } else {
            callback1(time)
        }

        if (subject.isEmpty()) {
            getAllSubject {
                callback2(it)
            }
        } else {
            callback2(subject)
        }

        if (teacher.isEmpty()) {
            getAllTeacher {
                callback3(it)
            }
        } else {
            callback3(teacher)
        }

        if (cabinet.isEmpty()) {
            getAllCabinet {
                callback4(it)
            }
        } else {
            callback4(cabinet)
        }
    }

    private fun getAllTime(callback: (List<Time>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.timeDao().getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                time.clear()
                time.addAll(it)
                callback(it)
            }
    }

    private fun getAllSubject(callback: (List<Subject>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.subjectDao().getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                subject.clear()
                subject.addAll(it)
                callback(it)
            }
    }

    private fun getAllTeacher(callback: (List<Teacher>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.teacherDao().getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                teacher.clear()
                teacher.addAll(it)
                callback(it)
            }
    }

    private fun getAllCabinet(callback: (List<Cabinet>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.cabinetDao().getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                cabinet.clear()
                cabinet.addAll(it)
                callback(it)
            }
    }

}