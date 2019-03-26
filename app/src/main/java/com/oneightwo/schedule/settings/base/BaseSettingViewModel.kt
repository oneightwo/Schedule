package com.oneightwo.schedule.settings.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseSettingViewModel<T> : ViewModel() {

    private val dataDeletion = MutableLiveData<ArrayList<FormDataSetting<T>>>()
    private val statusFAB = MutableLiveData<Boolean>()

    abstract val cls: Class<T>
    abstract val data: LiveData<List<T>>

    init {
        statusFAB.value = true
        dataDeletion.value = arrayListOf()
    }

    fun getStatusFAB() = statusFAB
    fun getDataDeletion() = dataDeletion


    fun addDataDeletions(position: Int, data: T) {
        dataDeletion.value?.add(FormDataSetting(position, data))
    }

    fun removeDataDeletions(position: Int, data: T) {
        dataDeletion.value?.remove(FormDataSetting(position, data))
    }

    fun listDataDeletions(): ArrayList<FormDataSetting<T>> = dataDeletion.value ?: arrayListOf()


    fun changeStateFAB(state: Boolean) {
        statusFAB.value = state
    }

    fun stateFAB() = statusFAB.value!!

    fun add(data: T) {
        val o = Observable.fromCallable {
            with(cls) {
                when {
                    isAssignableFrom(Cabinet::class.java) -> App.db.cabinetDao().insert(data as Cabinet)
                    isAssignableFrom(Time::class.java) -> App.db.timeDao().insert(data as Time)
                    isAssignableFrom(Teacher::class.java) -> App.db.teacherDao().insert(data as Teacher)
                    isAssignableFrom(Subject::class.java) -> App.db.subjectDao().insert(data as Subject)
                }
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun delete(data: ArrayList<FormDataSetting<T>>) {
        val o = Observable.fromCallable {
            for (i in data) {
                with(cls) {
                    when {
                        isAssignableFrom(Cabinet::class.java) -> App.db.cabinetDao().delete(i.data as Cabinet)
                        isAssignableFrom(Time::class.java) -> App.db.timeDao().delete(i.data as Time)
                        isAssignableFrom(Teacher::class.java) -> App.db.teacherDao().delete(i.data as Teacher)
                        isAssignableFrom(Subject::class.java) -> App.db.subjectDao().delete(i.data as Subject)
                    }
                }
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                dataDeletion.value = arrayListOf()
            }
    }

    fun update(data: T) {
        val o = Observable.fromCallable {
            with(cls) {
                when {
                    isAssignableFrom(Cabinet::class.java) -> App.db.cabinetDao().update(data as Cabinet)
                    isAssignableFrom(Time::class.java) -> App.db.timeDao().update(data as Time)
                    isAssignableFrom(Teacher::class.java) -> App.db.teacherDao().update(data as Teacher)
                    isAssignableFrom(Subject::class.java) -> App.db.subjectDao().update(data as Subject)
                }
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


}