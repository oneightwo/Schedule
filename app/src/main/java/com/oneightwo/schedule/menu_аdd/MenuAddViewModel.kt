package com.oneightwo.schedule.menu_аdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.tools.App
import com.oneightwo.schedule.tools.log

class MenuAddViewModel : ViewModel() {

    private val liveData: LiveData<List<Schedule>> = App.db.scheduleDao().getAllData()
    private val storageData: Schedule? = null

    private var temporaryStorage = MutableLiveData<MutableMap<Int, String>>()
    private val timeDao = App.db.timeDao()
    private val teacherDao = App.db.teacherDao()
    private val subjectDao = App.db.subjectDao()
    private val cabinetDao = App.db.cabinetDao()

    private val time = timeDao.getAllData()
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


    fun deleteData(position: Int) {

    }

    fun getTime() = time

    fun getTeacher() = teacher

    fun getSubject() = subject

    fun getCabinet() = cabinet

    fun getData(position: Int): List<String> = when (position) {
        0 -> DAY_OF_WEEK
        1 -> TYPE_WEEK
        2 -> time.value?.map { it.firstTime } ?: arrayListOf()
        3 -> time.value?.map { it.secondTime } ?: arrayListOf()
        4 -> subject.value?.map { it.subject } ?: arrayListOf()
        5 -> cabinet.value?.map { it.cabinet } ?: arrayListOf()
        6 -> teacher.value?.map { it.teacher } ?: arrayListOf()
        7 -> TYPE_LESSON
        else -> listOf()
    }

    companion object {
        val DAY_OF_WEEK = listOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота")
        val TYPE_WEEK = listOf("Четная", "Нечетная")
        val TYPE_LESSON = listOf("Лекция", "Практика", "Лабораторная")
    }

}