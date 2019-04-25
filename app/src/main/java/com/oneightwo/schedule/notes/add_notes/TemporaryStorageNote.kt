package com.oneightwo.schedule.notes.add_notes

import com.oneightwo.schedule.tools.ITEM_ADD_NOTE_MENU

class TemporaryStorageNote {
    var topic: String? = null
    var subject: String? = null
    var note: String? = null
    var time: String? = null
    var notification: Boolean? = null

    val oldTime = ""
    val oldNote = ""

    fun get(): List<Any> {
        val data = arrayListOf<Any>()
        data.add(topic ?: ITEM_ADD_NOTE_MENU[0])
        data.add(subject ?: ITEM_ADD_NOTE_MENU[1])
        data.add(note ?: ITEM_ADD_NOTE_MENU[2])
        data.add(time ?: ITEM_ADD_NOTE_MENU[3])
        data.add(notification ?: false)
        return data
    }


    fun isFilledMain() =
        topic != null && subject != null && note != null && notification != null

}