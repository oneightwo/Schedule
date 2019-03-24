package com.oneightwo.schedule.settings.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.base.BaseDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel<T, D : BaseDao<T>> : ViewModel() {

    private val positionStorage = MutableLiveData<ArrayList<Int>>()
    private val statusFAB = MutableLiveData<Boolean>()
    val deleteData = arrayListOf<T>()

    abstract fun getDao(): D

    abstract val data: LiveData<List<T>>

    init {
        statusFAB.value = true
    }

    fun getStatusFAB() = statusFAB
    fun getPositionStorage() = positionStorage

    fun add(data: T) {
        val o = Observable.fromCallable {
            getDao().insert(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun delete(data: T) {
        val o = Observable.fromCallable {
            //            dao.delete(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun update(data: T) {
        val o = Observable.fromCallable {
            //            dao.update(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


}