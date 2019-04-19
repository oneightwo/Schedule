package com.oneightwo.schedule.dialog_add

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.dialog_add.adapter.AddListDialogAdapter
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import kotlinx.android.synthetic.main.dialog_day_of_week.view.*

class AddListDialog(
    private val context: MenuAddActivity,
    private val getData: () -> List<String>,
    private val setData: (String) -> Unit,
    private val title: String
) : AlertDialog(context) {

    private val adapterDialog by lazy {
        AddListDialogAdapter(setData)
    }

    init {
        val view = context.layoutInflater.inflate(R.layout.dialog_add_list, null)
        with(view) {
            name_record_tv.text = title
            initRecyclerView(view)
            adapterDialog.add(
                getData()
            )
        }
        setView(view)
    }

    private fun initRecyclerView(view: View) {
        with(view) {
            hint_rv.layoutManager = LinearLayoutManager(context)
            hint_rv.adapter = adapterDialog
        }
    }

}