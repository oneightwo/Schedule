package com.oneightwo.schedule.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oneightwo.schedule.database.notes.Notes
import com.oneightwo.schedule.tools.App

class NotesViewModel : ViewModel() {

    private val notesDao = App.db.notesDao()
    private val notes: LiveData<List<Notes>> = notesDao.getAllData()

    fun getNotes() = notes



}