package com.oneightwo.schedule.schedule.dialog_add

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.schedule.add_time.AddTimeActivity
import com.oneightwo.schedule.schedule.dialog_add.adapter.AddTimeDialogAdapter
import com.oneightwo.schedule.schedule.menu_аdd.MenuAddActivity
import com.oneightwo.schedule.schedule.menu_аdd.TimeBell
import kotlinx.android.synthetic.main.dialog_add_time.view.*


class AddTimeDialog(
    private val context: MenuAddActivity,
    private val getData: () -> List<TimeBell>,
    private val setData: (String) -> Unit
) : AlertDialog(context) {

    private val adapterDialog by lazy {
        AddTimeDialogAdapter(setData)
    }

    init {
        val view = context.layoutInflater.inflate(R.layout.dialog_add_time, null)
        with(view) {
            initRecyclerView(view)
            adapterDialog.add(
                getData()
            )
            create_b.setOnClickListener {
                startNewActivity()
                dismiss()
            }
            cancel_b.setOnClickListener {
                dismiss()
            }
        }
        setView(view)
    }

    private fun startNewActivity() {
        val intent = Intent(context, AddTimeActivity::class.java)
        startActivity(context, intent, null)
    }

    private fun initRecyclerView(view: View) {
        with(view) {
            hint_rv.layoutManager = LinearLayoutManager(context)
            hint_rv.adapter = adapterDialog
        }
    }

}