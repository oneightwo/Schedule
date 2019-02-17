package com.oneightwo.schedule.settings

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType.*
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.fragment_item_setting.*


class ItemSettingFragment : BaseFragment() {

  //  private val adapterItemSettings by lazy { SubjectAdapter(::deleteData) }
//    private val adapterItemSettings by lazy { SubjectAdapter(::deleteData, viewModel.position) }
    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(SettingViewModel::class.java)
    }

    companion object {
        fun newInstance(position: Int): Fragment {
            val fragment = ItemSettingFragment()
            val bundle = Bundle()
            bundle.putInt("pos", position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId() = R.layout.fragment_item_setting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        viewModel.position = bundle?.getInt("pos") ?: 0

        clickFloatingActionButton()
        initRecyclerView(view)
    }

    private fun deleteData(index: Int) {

    }

    private fun clickFloatingActionButton() {
        add_fab.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_add, null)
            val dialog = AlertDialog.Builder(context)
                .setView(view)
                .create()
            with(view) {
                formatInput(view)
                save_b.setOnClickListener {
                    if (!add_data_et.text.isNullOrEmpty()) {
                       // addData(add_data_et.text.toString())
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
        with(view) {
            when (viewModel.position) {
                0 -> {
                    add_hint_data_tv.hint = context.getString(R.string.example_subject)
                    add_data_et.inputType = TYPE_CLASS_TEXT + TYPE_TEXT_FLAG_CAP_SENTENCES
                    name_record_tv.text = context.getText(R.string.subject)
                }
                1 -> {
                    add_hint_data_tv.hint = context.getString(R.string.example_time)
                    add_data_et.inputType = TYPE_CLASS_DATETIME
                    name_record_tv.text = context.getText(R.string.time)
                }
                2 -> {
                    add_hint_data_tv.hint = context.getString(R.string.example_cabinet)
                    add_data_et.inputType = TYPE_CLASS_NUMBER
                    name_record_tv.text = context.getText(R.string.cabinet)
                }
                3 -> {
                    add_hint_data_tv.hint = context.getString(R.string.example_teacher)
                    add_data_et.inputType = TYPE_TEXT_VARIATION_PERSON_NAME + TYPE_TEXT_FLAG_CAP_WORDS
                    name_record_tv.text = context.getText(R.string.teacher)
                }
            }
        }
    }


    private fun initRecyclerView(view: View) {
        //adapterItemSettings.clear()
        set_subjects_rv.layoutManager = LinearLayoutManager(view.context)
       // set_subjects_rv.adapter = adapterItemSettings
        //set_subjects_rv.itemAnimator = null
    }

}
