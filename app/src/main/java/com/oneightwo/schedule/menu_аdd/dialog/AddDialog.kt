package com.oneightwo.schedule.menu_аdd.dialog

import android.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import kotlinx.android.synthetic.main.dialog_day_of_week.view.*


class AddDialog(
    private val context: MenuAddActivity
//    private val getData: (Int) -> List<String>
) {

    private val adapterAddDialog by lazy {
        AddDialogAdapter()
    }

    private val view by lazy {
        context.layoutInflater.inflate(R.layout.dialog_day_of_week, null)
    }

    private val dialog by lazy {
        AlertDialog.Builder(context)
            .setView(view)
            .create()
    }

    private fun initAdapterAddDialog() {
        with(view) {
            hint_rv.layoutManager = LinearLayoutManager(context)
            hint_rv.adapter = adapterAddDialog
        }
    }

//    fun initDialog(position: Int) {
//        when (position) {
//            0 ->
//                1
//            ->
//                2
//            ->
//                3
//            ->
//                4
//            ->
//                5
//            ->
//                6
//            ->
//                7
//            ->
//            else ->
//        }
//    }

    fun show() {
        with(view) {
            initAdapterAddDialog()
            adapterAddDialog.add(
                arrayListOf(
                    "Математика",
                    "Русский",
                    "Ино",
                    "БД",
                    "Технология программирования",
                    "3Д моделирование",
                    "Физкультура",
                    "Введение в Qt"
                )
            )
            save_b.setOnClickListener {
                //                if (!add_first_time_et.text.isNullOrEmpty() && !add_second_time_et.text.isNullOrEmpty()) {

                dialog.dismiss()
//                }
            }
            cancel_b.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}