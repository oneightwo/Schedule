package com.oneightwo.schedule.settings.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.base.BaseDao
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.cabinet.CabinetDao
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.subject.SubjectDao
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.database.teacher.TeacherDao
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.database.time.TimeDao
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel<T, D : BaseDao<T>> : ViewModel() {

    private val positionStorage = MutableLiveData<ArrayList<Int>>()
    private val statusFAB = MutableLiveData<Boolean>()
    val deleteData = arrayListOf<T>()
    abstract val cls: Class<D>


    abstract val data: LiveData<List<T>>


//    fun <D: BaseDao<T>> setDao(cls: Class<T>) {
//        dao = with(cls) {
//            when {
//                isAssignableFrom(Cabinet::class.java) -> App.db.cabinetDao() as D
//                else -> throw IllegalArgumentException("Unknown")
//            }
//        }
//        log("DAO -> ${dao}")
//    }

    init {
        statusFAB.value = true
    }

    fun getStatusFAB() = statusFAB
    fun getPositionStorage() = positionStorage

    fun add(data: T) {
        val o = Observable.fromCallable {
            getDao(cls).insert(data)
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

    @Suppress("UNCHECKED_CAST")
    private fun <D> getDao(daoClass: Class<D>): D = with(daoClass) {
        when {
            isAssignableFrom(CabinetDao::class.java) -> App.db.cabinetDao()
            isAssignableFrom(TimeDao::class.java) -> App.db.timeDao()
            isAssignableFrom(TeacherDao::class.java) -> App.db.teacherDao()
            isAssignableFrom(SubjectDao::class.java) -> App.db.subjectDao()
            else -> throw IllegalArgumentException("Err")
        } as D
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