package com.oneightwo.schedule.notes.add_notes.dialog

import android.app.Activity
import com.oneightwo.schedule.notes.add_notes.AddNoteActivity.Companion.SUBJECT_POSITION
import com.oneightwo.schedule.notes.add_notes.AddNoteActivity.Companion.TIME_POSITION
import com.oneightwo.schedule.schedule.dialog_add.AddListDialog

class DialogManager(
    private val context: Activity,
    private val getData: () -> List<String>,
    private val setTime: (String) -> Unit,
    private val setDate: (String) -> Unit,
    private val position: Int,
    private val orientation: Int
) {

    private var dialogAddList: AddListDialog? = null
    private var dialogAddTime: AddTimeDialog? = null
    private var dialogAddDate: AddDateDialog? = null

    private var text = ""

    init {
        when (position) {
            SUBJECT_POSITION -> dialogAddList = AddListDialog(context, getData, ::setData, "Предмет")
            TIME_POSITION -> {
                dialogAddTime = AddTimeDialog(context, ::setTimeDialog)
                dialogAddDate = AddDateDialog(context, ::setDateDialog)
            }
        }
    }


    private fun setTimeDialog(time: String) {
        setTime(time)
    }

    private fun setDateDialog(date: String) {
        setDate(date)
    }

    fun setData(data: String) {
//        setData(position, data)
        dialogAddList?.dismiss()
        dialogAddTime?.dismiss()
    }

    fun show() {
        when (position) {
            SUBJECT_POSITION -> dialogAddList?.show()
            TIME_POSITION -> {
                dialogAddTime?.show()
                dialogAddDate?.show()
            }
        }
    }

    fun dismiss() {
        when (position) {
            SUBJECT_POSITION -> {
                if (dialogAddList != null)
                    dialogAddList?.dismiss()
            }
            TIME_POSITION -> dialogAddTime?.dismiss()
        }
    }
}