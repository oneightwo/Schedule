package com.oneightwo.schedule.settings.viewModel

import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SubjectViewModel : ViewModel() {

    private var data = arrayListOf<Subject>()
    private var positionStorage = arrayListOf<Int>()

    fun addPosition(position: Int) {
        positionStorage.add(position)
    }

    fun removePosition(position: Int) {
        positionStorage.remove(position)
    }

    fun getPositions() = ArrayList<Int>(positionStorage)



    fun getAll(callback: (List<Subject>) -> Unit) {
        if (data.isEmpty()) {
            getAllData {
                callback(it)
            }
        } else {
            callback(data)
        }
    }

    private fun getAllData(callback: (List<Subject>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.subjectDao().getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                data.clear()
                data.addAll(it)
                callback(it)
            }
    }

    fun deleteData(data: Subject, callback: (List<Subject>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.subjectDao().delete(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                getAllData{
                    callback(it)
                }
            }
    }

    fun add(data: Subject, callback: (List<Subject>) -> Unit) {
        val o = Observable.fromCallable {
            App.db.subjectDao().insertAll(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                getAllData{
                    callback(it)
                }
            }

    }

}