package com.oneightwo.schedule.menu_аdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.database.time.TimeEnd
import com.oneightwo.schedule.database.time.TimeStart
import com.oneightwo.schedule.tools.App
import com.oneightwo.schedule.tools.log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MenuAddViewModel : ViewModel() {

    private val liveData: LiveData<List<Schedule>> = App.db.scheduleDao().getAllData()
    private val storageData: Schedule? = null

    private var temporaryStorage = MutableLiveData<MutableMap<Int, String>>()

    private val timeStartDao = App.db.timeStartDao()
    private val timeEndDao = App.db.timeEndDao()
    private val teacherDao = App.db.teacherDao()
    private val subjectDao = App.db.subjectDao()
    private val cabinetDao = App.db.cabinetDao()
    private val scheduleDao = App.db.scheduleDao()

    private val timeStart = timeStartDao.getAllData()
    private val timeEnd = timeEndDao.getAllData()
    private val teacher = teacherDao.getAllData()
    private val subject = subjectDao.getAllData()
    private val cabinet = cabinetDao.getAllData()

    var position: Int? = null


    init {
        temporaryStorage.value = mutableMapOf()
    }

    fun setData(data: String) {
        log("$position $data")
        temporaryStorage.value = temporaryStorage.value?.apply { put(position!!, data) }
    }


    fun getTemporaryStorage() = temporaryStorage

    fun deleteData(data: String) {
        val o = Observable.fromCallable {
            log("DELETE $data")
            when (position) {
                2 -> timeStartDao.delete(timeStart.value?.first { it.time.equals(data) }!!)
                3 -> timeEndDao.delete(timeEnd.value?.first { it.time.equals(data) }!!)
                4 -> subjectDao.delete(subject.value?.first { it.subject.equals(data) }!!)
                5 -> cabinetDao.delete(cabinet.value?.first { it.cabinet.equals(data) }!!)
                6 -> teacherDao.delete(teacher.value?.first { it.teacher.equals(data) }!!)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    fun getStartTime() = timeStart
    fun getEndTime() = timeEnd
    fun getTeacher() = teacher
    fun getSubject() = subject
    fun getCabinet() = cabinet

    fun getData(): List<String> = when (position) {
        0 -> TYPE_WEEK
        1 -> DAY_OF_WEEK
        2 -> timeStart.value?.map { it.time } ?: arrayListOf()
        3 -> timeEnd.value?.map { it.time } ?: arrayListOf()
        4 -> subject.value?.map { it.subject } ?: arrayListOf()
        5 -> cabinet.value?.map { it.cabinet } ?: arrayListOf()
        6 -> teacher.value?.map { it.teacher } ?: arrayListOf()
        7 -> TYPE_LESSON
        else -> listOf()
    }

    fun insert() {
        val o = Observable.fromCallable {
            val data = temporaryStorage.value

            if (data?.get(2) != null && data[3] != null) {
                timeStartDao.insert(TimeStart(0, data[2]!!))
                timeEndDao.insert(TimeEnd(0, data[3]!!))
            }
            if (data?.get(6) != null) teacherDao.insert(Teacher(0, data[6]!!))
            if (data?.get(5) != null) cabinetDao.insert(Cabinet(0, data[5]!!))
            if (data?.get(4) != null) subjectDao.insert(Subject(0, data[4]!!))
            if (data?.get(0) != null && data[1] != null && data[2] != null && data[3] != null && data[4] != null) {
                log("ADD_DB_SCHEDULE")
                scheduleDao.insert(
                    Schedule(
                        id = 0,
                        week = data[0]!!,
                        day = data[1]!!,
                        firstTime = data[2]!!,
                        secondTime = data[3]!!,
                        subject = data[4]!!,
                        cabinet = data[5],
                        teacher = data[6],
                        type = data[7]
                    )
                )
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                temporaryStorage.value = mutableMapOf()
            }
    }

    companion object {
        val DAY_OF_WEEK = listOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота")
        val TYPE_WEEK = listOf("Четная", "Нечетная")
        val TYPE_LESSON = listOf("Лекция", "Практика", "Лабораторная")
    }

}