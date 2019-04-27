package com.oneightwo.schedule.notes.add_notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.notes.Notes
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.notes.add_notes.AddNoteActivity.Companion.SUBJECT_POSITION
import com.oneightwo.schedule.notes.add_notes.AddNoteActivity.Companion.TIME_POSITION
import com.oneightwo.schedule.tools.App
import com.oneightwo.schedule.tools.log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddNoteViewModel : ViewModel() {

    private val temporaryStorage = MutableLiveData<TemporaryStorageNote>()
    private val notesDao = App.db.notesDao()
    private val subject: LiveData<List<Subject>> = App.db.subjectDao().getAllData()

    init {
        temporaryStorage.value = TemporaryStorageNote()
    }

    fun getTemporaryStorage() = temporaryStorage
    fun getSubject() = subject

    fun setData(position: Int, data: Any) {
        log("$position -> $data")
        with(temporaryStorage) {
            value = when (position) {
                0 -> value.apply { value?.topic = data as String }
                1 -> value.apply { value?.subject = data as String }
                2 -> value.apply { value?.note = data as String }
                3 -> value.apply { value?.time = data as String }
                4 -> value.apply { value?.notification = data as Boolean }
                else -> return
            }

        }
    }

    fun setDate(date: String) {
        with(temporaryStorage) {
            value = value?.apply { value?.time = date }
        }
    }

    fun setTime(time: String) {
        with(temporaryStorage) {
            value = value?.apply { value?.time = "${value?.time}  $time" }
        }
    }

    fun getData() = subject.value?.map { it.subject } ?: arrayListOf()

    fun setDataDialog(position: Int, data: String) {
        with(temporaryStorage) {
            when (position) {
                SUBJECT_POSITION -> value = value?.apply { value?.subject = data }
                TIME_POSITION -> value = value?.apply { value?.time = data }
            }
        }
    }

    fun insert() {
        val o = Observable.fromCallable {
            with(temporaryStorage) {
                notesDao.insert(
                    Notes(
                        id = 0,
                        topic = value?.topic!!,
                        subject = value?.subject!!,
                        note = value?.note!!,
                        time = value?.time,
                        notification = value?.notification!!
                    )
                )
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

            }
    }
}