package com.oneightwo.schedule.add_time

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.tools.App
import com.oneightwo.schedule.tools.IC_NUMBER_BELL
import com.oneightwo.schedule.tools.log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddTimeViewModel : ViewModel() {

    private val temporaryStorage = MutableLiveData<ArrayList<TimeTemporaryStorage>>()
    private val timeDao = App.db.timeDao()

    private val time: LiveData<List<Time>> = timeDao.getAllData()


    var position: Int? = null
    var isActiveDialog = false

    init {
        temporaryStorage.value = arrayListOf()
//        val data = time.value!!
//        with(temporaryStorage) {
//            if (data.isEmpty()) {
//                value = value?.apply {
//                    add(TimeTemporaryStorage())
//                    value?.get(0)?.number = 0
//                }
//            } else {
//                val list = arrayListOf<TimeTemporaryStorage>()
//                for ((i, v) in data.withIndex()) {
//                    list.add(TimeTemporaryStorage())
//                    list[i].number = v.number
//                    list[i].start = v.time.replace(" ", "").substringBefore('-')
//                    list[i].end = v.time.replace(" ", "").substringAfter('-')
//                }
//                log("LIST = $list")
//                value = value?.apply {
//                    list.forEach { add(it) }
//                }
//            }
//        }

    }

    fun initBells(data: List<Time>) {
        with(temporaryStorage) {
            if (data.isEmpty()) {
                value = value?.apply {
                    add(TimeTemporaryStorage())
                    value?.get(0)?.number = 0
                }
            } else {
                value = arrayListOf()
                val list = arrayListOf<TimeTemporaryStorage>()
                for ((i, v) in data.withIndex()) {
                    list.add(TimeTemporaryStorage())
                    list[i].number = v.number
                    list[i].start = v.time.replace(" ", "").substringBefore('-')
                    list[i].end = v.time.replace(" ", "").substringAfter('-')
                }
                log("LIST = $list")
                value = value?.apply {
                    list.forEach { add(it) }
                }
            }
        }
    }


    fun dismissDialog() {
        isActiveDialog = false
    }

    fun getTime() = time

    fun getTimeStorage() = temporaryStorage

    fun addLine(size: Int) {
        with(temporaryStorage) {
            value = value?.apply {
                add(TimeTemporaryStorage())
                value?.get(size)?.number = if (value?.get(size - 1)?.number!! + 1 < IC_NUMBER_BELL.size) {
                    value?.get(size - 1)?.number!! + 1
                } else {
                    0
                }
            }
        }
    }

    fun saveStartTime(position: Int, data: String) {
        with(temporaryStorage) {
            value = value.apply {
                value?.get(position)?.start = data
            }
        }
    }

    fun saveEndTime(position: Int, data: String) {
        with(temporaryStorage) {
            value = value.apply {
                value?.get(position)?.end = data
            }
        }
    }

    fun saveNumberTime(number: Int) {
        with(temporaryStorage) {
            value = value.apply {
                value?.get(position ?: return)?.number = number
            }
        }
    }

    fun isFilledAllFields(): Boolean {
        for (i in temporaryStorage.value!!) {
            if (i.start == null || i.end == null) {
                return false
            }
        }
        return true
    }

    fun insert() {
        val o = Observable.fromCallable {
            val list = temporaryStorage.value?.map { Time(0, "${it.start} - ${it.end}", it.number!!) }!!.toTypedArray()
            for ((i, v) in time.value!!.withIndex()) {
                if (!v.time.equals(list[i].time) || !v.number.equals(list[i].number)) {
                    timeDao.update(Time(v.id, list[i].time, list[i].number))
                }
            }
            val listFinal = list.drop(time.value!!.size).toTypedArray()
            log("${list.map { it.time }}")
            timeDao.insert(*listFinal)

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                temporaryStorage.value = arrayListOf()
                log("INSERT_TIME")
            }
    }

}