package com.oneightwo.schedule.schedule.dialog_add

import android.content.res.Configuration
import com.oneightwo.schedule.R
import com.oneightwo.schedule.schedule.menu_аdd.MenuAddActivity
import com.oneightwo.schedule.schedule.menu_аdd.TimeBell
import com.oneightwo.schedule.tools.TITLE

class DialogManager (
    private val context: MenuAddActivity,
    private val position: Int,
    private val dataString: () -> List<String>,
    private val dataTimeBell: List<TimeBell>,
    private val setData: (String) -> Unit,
    private val deleteData: (String) -> Unit,
    private val dismissDialog: () -> Unit,
    private val orientation: Int
) {

    private var dialogAdd: AddDialog? = null
    private var dialogAddList: AddListDialog? = null
    private var dialogAddTime: AddTimeDialog? = null

    init {
        when (position) {
            0, 1, 6 -> dialogAddList = AddListDialog(
                context, dataString, setData, TITLE[position], dismissDialog
            )
            2 -> dialogAddTime = AddTimeDialog(
                context, dataTimeBell, setData
            )
            else -> dialogAdd = AddDialog(
                context, dataString, setData, deleteData, if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    R.layout.dialog_add_et_and_list_horizontal
                } else {
                    R.layout.dialog_add_et_and_list
                }
                , TITLE[position]
            )
        }
    }

    fun show() {
        when (position) {
            0, 1, 6 -> dialogAddList?.show()
            2 -> dialogAddTime?.show()
            else -> dialogAdd?.show()
        }
    }

    fun dismiss() {
        when (position) {
            0, 1, 6 -> {
                if (dialogAddList != null) {
                    dialogAddList?.dismiss()
                    dismissDialog()
                }
            }
            2 -> dialogAddTime?.dismiss()
            else -> dialogAdd?.dismiss()
        }
    }


    fun update() {
        if (dialogAdd != null) dialogAdd?.update()
    }
}