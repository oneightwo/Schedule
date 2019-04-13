package com.oneightwo.schedule.dialog_add

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.dialog_add.adapter.AddDialogAdapter
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import kotlinx.android.synthetic.main.dialog_day_of_week.view.*


class AddDialog(
    private val context: MenuAddActivity,
    private val getData: () -> List<String>,
    private val setData: (String) -> Unit,
    private val deleteData: (String) -> Unit,
    private val layout: Int
) : AlertDialog(context) {

    private val adapterDialog by lazy {
        AddDialogAdapter(
            setData,
            deleteData
        )
    }

//    private fun deleteAndUpdate(data: String) {
//        deleteData(data) {
//            log("$it")
//            adapterDialog.update(it)
//        }
//
//    }

    init {
        val view = context.layoutInflater.inflate(layout, null)
        with(view) {
            initRecyclerView(view)
            adapterDialog.add(
                getData()
            )
            save_b.setOnClickListener {
                if (!add_data_et.text.isNullOrEmpty()) {
                    setData(add_data_et.text.toString())
                }
                dismiss()
            }
            cancel_b.setOnClickListener {
                dismiss()
            }
        }

        setView(view)

    }

    fun update() {
        adapterDialog.update(
            getData()
        )
    }

    private fun initRecyclerView(view: View) {
        with(view) {
            hint_rv.layoutManager = LinearLayoutManager(context)
            hint_rv.adapter = adapterDialog
        }
    }

}