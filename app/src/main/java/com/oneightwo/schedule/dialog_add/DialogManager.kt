package com.oneightwo.schedule.dialog_add

import android.content.res.Configuration
import com.oneightwo.schedule.R
import com.oneightwo.schedule.menu_аdd.MenuAddActivity

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
    private var dialogAddTime: AddTimeDialog? = null

    init {
        when (position) {
            0, 1, 6 -> dialogAddList = AddListDialog(
                context, getData, setData
            )
            2 -> dialogAddTime = AddTimeDialog(
                context, getData, setData
            )
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
            0, 1, 6 -> dialogAddList?.show()
            2 -> dialogAddTime?.show()
            else -> dialogAdd?.show()
        }
    }

    fun dismiss() {
        when (position) {
            0, 1, 6 -> {
                if (dialogAddList != null)
                    dialogAddList?.dismiss()
            }
            2 -> dialogAddTime?.dismiss()
            else -> dialogAdd?.dismiss()
        }
    }


    fun update() {
        if (dialogAdd != null) dialogAdd?.update()
    }
}