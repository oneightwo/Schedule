//package com.oneightwo.schedule.settings.adapter
//
//import com.oneightwo.schedule.database.subject.Subject
//import com.oneightwo.schedule.settings.base.BaseSettingAdapter
//
//class SubjectAdapter(
//    private val addPosition: (Int, Subject) -> Unit,
//    private val removePosition: (Int, Subject) -> Unit,
//    private val getPositions: () -> ArrayList<Int>,
//    private val setLongClick: (Boolean) -> Unit,
//    private val isLongClick: () -> Boolean
//): BaseSettingAdapter<Subject>(addPosition, removePosition, getPositions, setLongClick, isLongClick) {
//
//    override fun getText(item: Subject): String {
//        return item.subject.toString()
//    }
//}