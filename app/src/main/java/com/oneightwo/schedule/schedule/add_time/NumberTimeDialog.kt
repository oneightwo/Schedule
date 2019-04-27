package com.oneightwo.schedule.schedule.add_time

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.tools.IC_NUMBER_BELL
import kotlinx.android.synthetic.main.dialog_number_bell_time.view.*

class NumberTimeDialog(
    private val context: AddTimeActivity,
    private val setNumberTime: (Int) -> Unit,
    private val dismissDialog: () -> Unit
) : AlertDialog(context) {

    private val adapterDialog by lazy {
        AddNumberTimeAdapter(::setNumberTimeDialog)
    }

    init {
        val view = context.layoutInflater.inflate(R.layout.dialog_number_bell_time, null)
        with(view) {
            initRecyclerView(view)
            adapterDialog.add(IC_NUMBER_BELL)
        }
        setView(view)
    }

    private fun setNumberTimeDialog(position: Int) {
        setNumberTime(position)
        dismiss()
    }

    override fun dismiss() {
        super.dismiss()
        dismissDialog()
    }

    private fun initRecyclerView(view: View) {
        with(view) {
            number_bell_rv.layoutManager = LinearLayoutManager(context)
            number_bell_rv.adapter = adapterDialog
        }
    }

}