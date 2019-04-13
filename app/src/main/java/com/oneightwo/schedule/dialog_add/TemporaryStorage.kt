package com.oneightwo.schedule.dialog_add

import com.oneightwo.schedule.tools.ITEM_ADD_MENU

class TemporaryStorage {
    var week: String? = null
    var type: String? = null
    var day: String? = null
    var subject: String? = null
    var time: String? = null
    var cabinet: String? = null
    var teacher: String? = null

    fun get(): List<String> {
        val data = arrayListOf<String>()
        data.add(week ?: ITEM_ADD_MENU[0])
        data.add(day ?: ITEM_ADD_MENU[1])
        data.add(time ?: ITEM_ADD_MENU[2])
        data.add(subject ?: ITEM_ADD_MENU[3])
        data.add(cabinet ?: ITEM_ADD_MENU[4])
        data.add(teacher ?: ITEM_ADD_MENU[5])
        data.add(type ?: ITEM_ADD_MENU[6])
        return data
    }

    fun isFilledMain() =
        week != null && day != null && subject != null && time != null

}


