package com.oneightwo.schedule.menu_аdd.dialog

import android.app.TimePickerDialog
import android.content.res.Configuration
import com.oneightwo.schedule.R
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import java.util.*

class DialogManager(
    private val context: MenuAddActivity,
    private val getData: () -> List<String>,
    private val setData: (String) -> Unit,
    private val deleteData: (String) -> Unit,
    private val position: Int,
    private val orientation: Int
) {

    private var dialogAdd: AddDialog? = null
    private var dialogAddList: AddListDialog? = null
    private var dialogTime: TimePickerDialog? = null

    init {
        when (position) {
            0, 1, 7 -> dialogAddList = AddListDialog(
                context, getData, setData
            )
            2, 3 -> {
                val c = Calendar.getInstance()
                val hour = c.get(Calendar.HOUR)
                val minute = c.get(Calendar.MINUTE)
                dialogTime = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(function = { _, h, m ->
                    setData("$h : $m")
                }), hour, minute, true)
            }
            else -> dialogAdd = AddDialog(
                context, getData, setData, deleteData, if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    R.layout.dialog_day_of_week_horizontal
                } else {
                    R.layout.dialog_day_of_week
                }
            )
        }
    }

    fun show() {
        when (position) {
            0, 1, 7 -> dialogAddList?.show()
            2, 3 -> dialogTime?.show()
            else -> dialogAdd?.show()
        }
    }

    fun dismiss() {
        when (position) {
            0, 1, 7 -> {
                if (dialogAddList != null)
                    dialogAddList?.dismiss()
            }
            2, 3 -> dialogTime?.dismiss()
            else -> dialogAdd?.dismiss()
        }
    }



    fun update() {
        if (dialogAdd != null) dialogAdd?.update()
    }
}