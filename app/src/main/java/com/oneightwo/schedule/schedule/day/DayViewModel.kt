package com.oneightwo.schedule.schedule.day

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.database.schedule.ScheduleDao

class DayViewModel : ViewModel() {

    var numberPage: Int? = null
//    private val data = arrayListOf<Schedule>()
//    private val liveData = MutableLiveData<Schedule>()

    lateinit var scheduleDao : ScheduleDao
    val day: LiveData<List<Schedule>> = scheduleDao.getAll()

//    fun getLiveData() = liveData

//    fun getAll(callback: (List<Schedule>) -> Unit) {
//        if (data.isEmpty()) {
//            getAllData {
//                callback(it)
////                callback(it.filter { it.day == numberPage })
//            }
//        } else {
//            callback(data)
////            callback(data.filter { it.day == numberPage })
//        }
//    }

//    private fun getAllData(callback: (List<Schedule>) -> Unit) {
//        val o = Observable.fromCallable {
//            App.db.scheduleDao().getAll()
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { list ->
//                data.clear()
//                data.addAll(list.filter { it.day == numberPage })
//                callback(list.filter { it.day == numberPage })
//            }
//    }

}