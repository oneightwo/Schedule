package com.oneightwo.schedule.schedule.menu_аdd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.schedule.dialog_add.TemporaryStorage
import com.oneightwo.schedule.tools.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MenuAddViewModel : ViewModel() {

    private var temporaryStorage = MutableLiveData<TemporaryStorage>()

    private val timeDao = App.db.timeDao()
    private val teacherDao = App.db.teacherDao()
    private val subjectDao = App.db.subjectDao()
    private val cabinetDao = App.db.cabinetDao()
    private val scheduleDao = App.db.scheduleDao()

    private var isUpdateData = MutableLiveData<Boolean>()
    private var id: Int? = null

    private val time = timeDao.getAllData()
    private val teacher = teacherDao.getAllData()
    private val subject = subjectDao.getAllData()
    private val cabinet = cabinetDao.getAllData()

    private val hint = arrayListOf<String>()

    var isActiveDialog = false
    var isFilledMain = true
    var position: Int? = null

    init {
        temporaryStorage.value = TemporaryStorage()
        isUpdateData.value = false
        hint.apply {
            add(TYPE_WEEK[0])
            add(DAY_OF_WEEK[0])
        }
    }


    fun initTemporaryStorage(data: Schedule) {
        isUpdateData.value = true
        id = data.id
        with(temporaryStorage) {
            value = value.apply { value?.week = TYPE_WEEK[data.week - 1] }
            value = value.apply { value?.day = DAY_OF_WEEK[data.day] }
            value = value.apply { value?.time = data.time }
            value = value.apply { value?.subject = data.subject }
            value = value.apply { value?.cabinet = data.cabinet }
            value = value.apply { value?.teacher = data.teacher }
            value = value.apply { value?.type = if (data.type != null) TYPE_LESSON[data.type] else null }
        }
    }

    fun initTemporaryStorage() {
        with(temporaryStorage) {
            value = value.apply {
                value?.week = hint[0]
                value?.day = hint[1]
            }
        }
    }

    fun setData(data: String) {
        log("setData = $data")
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
    fun getIsUpdateData() = isUpdateData

    fun getDataString(): List<String> {
        log("GET_DATA")
        return when (position) {
            0 -> TYPE_WEEK
            1 -> DAY_OF_WEEK
            3 -> subject.value?.map { it.subject } ?: arrayListOf()
            4 -> cabinet.value?.map { it.cabinet } ?: arrayListOf()
            5 -> teacher.value?.map { it.teacher } ?: arrayListOf()
            6 -> TYPE_LESSON
            else -> listOf()
        }
    }

    fun getData(): List<TimeBell> {
        log("GET_DATA")
        return time.value?.map { TimeBell(it.number, it.time) } ?: arrayListOf()
    }

    fun save() {
        if (isUpdateData.value!!) update()
        else insert()
    }

    private fun update() {
        val o = Observable.fromCallable {
            val data = temporaryStorage.value!!
            scheduleDao.update(
                Schedule(
                    id = id!!,
                    week = TYPE_WEEK.indexOf(data.week) + 1,
                    day = DAY_OF_WEEK.indexOf(data.day),
                    time = data.time!!,
                    subject = data.subject!!,
                    cabinet = data.cabinet,
                    teacher = data.teacher,
                    type = TYPE_LESSON.indexOf(data.type)
                )
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                temporaryStorage.value = TemporaryStorage()
                id = null
                isUpdateData.value = false
            }

    }

    private fun insert() {
        val o = Observable.fromCallable {
            val data = temporaryStorage.value
            if (data?.teacher != null) teacherDao.insert(Teacher(data.teacher!!))
            if (data?.cabinet != null) cabinetDao.insert(Cabinet(data.cabinet!!))
            subjectDao.insert(Subject(data?.subject!!))

            hint[0] = data.week!!
            hint[1] = data.day!!

            log("ADD_DB_SCHEDULE")
            scheduleDao.insert(
                Schedule(
                    id = 0,
                    week = TYPE_WEEK.indexOf(data.week!!) + 1,
                    day = DAY_OF_WEEK.indexOf(data.day!!),
                    time = data.time!!,
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

    fun delete() {
        val o = Observable.fromCallable {
            val data = temporaryStorage.value!!
            scheduleDao.delete(
                Schedule(
                    id = id!!,
                    week = TYPE_WEEK.indexOf(data.week) + 1,
                    day = DAY_OF_WEEK.indexOf(data.day),
                    time = data.time!!,
                    subject = data.subject!!,
                    cabinet = data.cabinet,
                    teacher = data.teacher,
                    type = TYPE_LESSON.indexOf(data.type)
                )
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                temporaryStorage.value = TemporaryStorage()
                id = null
                isUpdateData.value = false
            }

    }

}