package com.oneightwo.schedule.menu_аdd.dialog

import android.app.TimePickerDialog
import android.content.pm.ActivityInfo
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import java.util.*

class DialogManager(
    private val context: MenuAddActivity,
    private val getData: () -> List<String>,
    private val setData: (String) -> Unit,
    private val deleteData: (String) -> Unit,
    private val position: Int
) {

    private var dialogAdd: AddDialog? = null
    private var dialogAddList: AddListDialog? = null

    init {
        when (position) {
            0, 1, 7 -> dialogAddList = AddListDialog(context, getData, setData)
            2, 3 -> {
                val c = Calendar.getInstance()
                val hour = c.get(Calendar.HOUR)
                val minute = c.get(Calendar.MINUTE)

                val tpd = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(function = { _, h, m ->
                    setData("$h : $m")
                }), hour, minute, true).show()
                orientation(true)
            }
            else -> dialogAdd = AddDialog(context, getData, setData, deleteData)
        }
    }

    fun show() {
        if (dialogAdd != null) dialogAdd?.show()
        else dialogAddList?.show()
        orientation(true)
    }

    fun dismiss() {
        if (dialogAdd != null) dialogAdd?.dismiss()
        else dialogAddList?.dismiss()
        orientation(false)
    }

    fun update() {
        if (dialogAdd != null) dialogAdd?.update()
    }

    private fun orientation(block: Boolean) {
        context.requestedOrientation = if (block) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }
    }
}