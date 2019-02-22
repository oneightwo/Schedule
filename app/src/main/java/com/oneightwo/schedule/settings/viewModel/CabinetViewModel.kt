package com.oneightwo.schedule.settings.viewModel

import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CabinetViewModel : ViewModel() {

    private var data = arrayListOf<Cabinet>()
    val positionStorage = arrayListOf<Int>()
    var isAdd = true
    val deleteData = arrayListOf<Cabinet>()

    // private lateinit var subject: LiveData<List<Subject>>

    fun getAll(callback: (List<Cabinet>) -> Unit) {
        if (data.isEmpty()) {
            getAllData {
                callback(it)
            }
        } else {
            callback(data)
        }
    }

    private fun getAllData(callback: (List<Cabinet>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.cabinetDao().getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                data.clear()
                data.addAll(it)
                callback(it)
            }
    }

    fun deleteData(callback: (List<Cabinet>) -> Unit) {
        val o = Observable.fromCallable {
            for (d in deleteData)
                App.db.cabinetDao().delete(d)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                getAllData{
                    callback(it)
                }
            }
    }

    fun add(data: Cabinet, callback: (List<Cabinet>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.cabinetDao().insertAll(data)
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