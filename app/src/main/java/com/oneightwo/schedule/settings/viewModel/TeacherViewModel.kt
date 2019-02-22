package com.oneightwo.schedule.settings.viewModel

import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TeacherViewModel : ViewModel() {

    private var data = arrayListOf<Teacher>()
    val positionStorage = arrayListOf<Int>()
    var isAdd = true
    val deleteData = arrayListOf<Teacher>()

    // private lateinit var subject: LiveData<List<Subject>>

    fun getAll(callback: (List<Teacher>) -> Unit) {
        if (data.isEmpty()) {
            getAllData {
                callback(it)
            }
        } else {
            callback(data)
        }
    }

    private fun getAllData(callback: (List<Teacher>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.teacherDao().getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                data.clear()
                data.addAll(it)
                callback(it)
            }
    }

    fun deleteData(callback: (List<Teacher>) -> Unit) {
        val o = Observable.fromCallable {
            for (d in deleteData)
                App.db.teacherDao().delete(d)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                getAllData{
                    callback(it)
                }
            }
    }

    fun add(data: Teacher, callback: (List<Teacher>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.teacherDao().insertAll(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                getAllData {
                    callback(it)
                }
            }
    }

    fun clear() {
        isAdd = true
        deleteData.clear()
        positionStorage.clear()
    }

}