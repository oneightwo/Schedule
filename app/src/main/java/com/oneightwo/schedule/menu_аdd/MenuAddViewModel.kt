package com.oneightwo.schedule.menu_аdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.dialog_add.TemporaryStorage
import com.oneightwo.schedule.tools.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MenuAddViewModel : ViewModel() {

    private val liveData: LiveData<List<Schedule>> = App.db.scheduleDao().getAllData()
    private val storageData: Schedule? = null

    private var temporaryStorage = MutableLiveData<TemporaryStorage>()

    private val timeDao = App.db.timeDao()
    private val teacherDao = App.db.teacherDao()
    private val subjectDao = App.db.subjectDao()
    private val cabinetDao = App.db.cabinetDao()
    private val scheduleDao = App.db.scheduleDao()

    private val time = timeDao.getAllData()
    private val teacher = teacherDao.getAllData()
    private val subject = subjectDao.getAllData()
    private val cabinet = cabinetDao.getAllData()

    var isActiveDialog = false
    var isFilledMain = true
    var position: Int? = null

    init {
        temporaryStorage.value = TemporaryStorage()
    }

    fun setData(data: String) {
        log("setData = $data")
//        temporaryStorageList = temporaryStorage.value?.get() ?: listOf()
        isActiveDialog = false
        with(temporaryStorage) {
            when (position) {
                0 -> value = value.apply { value?.week = data }
                1 -> value = value.apply { value?.day = data }
                2 -> value = value.apply { value?.time = data }
                3 -> value = value.apply { value?.subject = data }
                4 -> value = value.apply { value?.cabinet = data }
                5 -> value = value.apply { value?.teacher = data }
                6 -> value = value.apply { value?.type = data }
                else -> ""
            }
        }
    }


    fun getTemporaryStorage() = temporaryStorage

    fun deleteData(data: String) {
        val o = Observable.fromCallable {
            log("DELETE $data")
            when (position) {
                2 -> timeDao.delete(time.value?.first { it.time.equals(data) }!!)
                3 -> timeDao.delete(time.value?.first { it.time.equals(data) }!!)
                4 -> subjectDao.delete(subject.value?.first { it.subject.equals(data) }!!)
                5 -> cabinetDao.delete(cabinet.value?.first { it.cabinet.equals(data) }!!)
                6 -> teacherDao.delete(teacher.value?.first { it.teacher.equals(data) }!!)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getTime() = time
    fun getTeacher() = teacher
    fun getSubject() = subject
    fun getCabinet() = cabinet

    fun getData(): List<String> {
        log("GET_DATA")
        return when (position) {
            0 -> TYPE_WEEK
            1 -> DAY_OF_WEEK
            2 -> time.value?.map { it.time } ?: arrayListOf()
            3 -> subject.value?.map { it.subject } ?: arrayListOf()
            4 -> cabinet.value?.map { it.cabinet } ?: arrayListOf()
            5 -> teacher.value?.map { it.teacher } ?: arrayListOf()
            6 -> TYPE_LESSON
            else -> listOf()
        }
    }

    fun insert() {
        val o = Observable.fromCallable {
            val data = temporaryStorage.value
            if (data?.teacher != null) teacherDao.insert(Teacher(data.teacher!!))
            if (data?.cabinet != null) cabinetDao.insert(Cabinet(data.cabinet!!))
            subjectDao.insert(Subject(data?.subject!!))

            log("ADD_DB_SCHEDULE")
            scheduleDao.insert(
                Schedule(
                    id = 0,
                    week = TYPE_WEEK.indexOf(data.week) + 1,
                    day = DAY_OF_WEEK.indexOf(data.day),
                    firstTime = data.time!!,
                    secondTime = data.time!!,
                    subject = data.subject!!,
                    cabinet = data.cabinet,
                    teacher = data.teacher,
                    type = if (data.type != null) TYPE_LESSON.indexOf(data.type!!) else null
                )
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                temporaryStorage.value = TemporaryStorage()
            }
    }

}