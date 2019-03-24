package com.oneightwo.schedule.settings.base

import android.text.InputType.*
import android.view.View
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import kotlinx.android.synthetic.main.dialog_add.view.*

abstract class BaseSettingFragment<T>(
    private val index: Int
//    private val vmClass: ViewModel
) : BaseFragment() {
//
//    private val viewModel by lazy {
//        ViewModelProviders.of(this)
//            .get(vmClass::class.java)
//    }

    abstract fun addData(data: String)

    abstract fun modeFAB()

    abstract fun clickDeleteFAB()

    abstract fun addPosition(position: Int, data: T)

    abstract fun removePosition(position: Int, data: T)

    abstract fun getPositions(): ArrayList<Int>

    abstract fun setLongClick(isClick: Boolean)

    abstract fun isLongClick(): Boolean


    abstract fun clickAddFAB(add: (String) -> Unit)

    fun formatInput(view: View) {
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
                    add_data_et.inputType = TYPE_CLASS_TEXT
                }
                3 -> {
                    hint = context.getString(R.string.example_teacher)
                    add_data_et.inputType = TYPE_TEXT_VARIATION_PERSON_NAME + TYPE_TEXT_FLAG_CAP_WORDS
                }
            }
        }
    }

}