package com.oneightwo.schedule.settings.viewModel

import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TimeViewModel : ViewModel() {

    private var data = arrayListOf<Time>()
    val positionStorage = arrayListOf<Int>()
    var isAdd = true
    val deleteData = arrayListOf<Time>()

    // private lateinit var subject: LiveData<List<Subject>>

    fun getAll(callback: (List<Time>) -> Unit) {
        if (data.isEmpty()) {
            getAllData {
                callback(it)
            }
        } else {
            callback(data)
        }
    }

    private fun getAllData(callback: (List<Time>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.timeDao().getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                data.clear()
                data.addAll(it)
                callback(it)
            }
    }

    fun deleteData(callback: (List<Time>) -> Unit) {
        val o = Observable.fromCallable {
            for (d in deleteData)
                App.db.timeDao().delete(d)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                getAllData{
                    callback(it)
                }
            }
    }

    fun add(data: Time, callback: (List<Time>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.timeDao().insertAll(data)
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