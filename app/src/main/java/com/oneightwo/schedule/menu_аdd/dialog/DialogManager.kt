package com.oneightwo.schedule.menu_аdd.dialog

import android.app.TimePickerDialog
import android.content.res.Configuration
import com.oneightwo.schedule.R
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import java.util.*

class DialogManager(
    private val context: MenuAddActivity,
    private val getData: () -> List<String>,
    private val setData: (Any) -> Unit,
    private val deleteData: (String) -> Unit,
    private val position: Int,
    private val orientation: Int
) {

    private var dialogAdd: AddDialog? = null
    private var dialogAddList: AddListDialog? = null


    init {
        when (position) {
            0, 1, 7 -> dialogAddList = AddListDialog(
                context, getData, setData)
            2, 3 -> {
                val c = Calendar.getInstance()
                val hour = c.get(Calendar.HOUR)
                val minute = c.get(Calendar.MINUTE)

                val tpd = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(function = { _, h, m ->
                    setData("$h : $m")
                }), hour, minute, true).show()
            }
            else -> dialogAdd = AddDialog(context, getData, setData, deleteData,  if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                R.layout.dialog_day_of_week_horizontal
            } else {
                R.layout.dialog_day_of_week
            })
        }
    }

    fun show() {
        if (dialogAdd != null) dialogAdd?.show()
        else dialogAddList?.show()
    }

    fun dismiss() {
        if (dialogAdd != null) dialogAdd?.dismiss()
        else dialogAddList?.dismiss()
    }

    fun update() {
        if (dialogAdd != null) dialogAdd?.update()
    }
}