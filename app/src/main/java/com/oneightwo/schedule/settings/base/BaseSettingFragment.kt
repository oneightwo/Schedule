package com.oneightwo.schedule.settings.base

import android.app.AlertDialog
import android.text.InputType.*
import android.view.View
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.fragment_item_setting.*

abstract class BaseSettingFragment<T>(
    private val index: Int
) : BaseFragment() {

    fun clickAddFAB(add: (String) -> Unit) {
        add_fab.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_add, null)
            val dialog = AlertDialog.Builder(context)
                .setView(view)
                .create()
            with(view) {
                formatInput(view)
                save_b.setOnClickListener {
                    if (!add_data_et.text.isNullOrEmpty()) {

                        add(add_data_et.text.toString())
                        dialog.dismiss()
                    }
                }
                cancel_b.setOnClickListener {
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }


    private fun formatInput(view: View) {
        with(view.add_hint_data_tv) {
            when (index) {
                0 -> {
                    hint = context.getString(R.string.example_subject)
                    add_data_et.inputType = TYPE_CLASS_TEXT + TYPE_TEXT_FLAG_CAP_SENTENCES
                }
                1 -> {
                    hint = context.getString(R.string.example_time)
                    add_data_et.inputType = TYPE_CLASS_DATETIME
                }
                2 -> {
                    hint = context.getString(R.string.example_cabinet)
                    add_data_et.inputType = TYPE_CLASS_NUMBER
                }
                3 -> {
                    hint = context.getString(R.string.example_teacher)
                    add_data_et.inputType = TYPE_TEXT_VARIATION_PERSON_NAME + TYPE_TEXT_FLAG_CAP_WORDS
                }
            }
        }
    }

//    abstract fun deleteData(data: T)

    abstract fun getAllData()

}