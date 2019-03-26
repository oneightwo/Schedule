package com.oneightwo.schedule.settings.fragment

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.settings.adapter.TeacherAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.TeacherViewModel
import kotlinx.android.synthetic.main.dialog_add.view.*

class TeacherFragment : BaseSettingFragment<Teacher>() {

    companion object {
        fun newInstance() = TeacherFragment()
    }

    override val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(TeacherViewModel::class.java)
    }

    override val adapterItemSettings by lazy {
        TeacherAdapter(
            viewModel::addDataDeletions,
            viewModel::removeDataDeletions,
            viewModel::listDataDeletions,
            viewModel::changeStateFAB,
            viewModel::stateFAB
        )
    }

    override fun initDialog(add: (Teacher) -> Unit) {
        val view = layoutInflater.inflate(R.layout.dialog_add, null)
        val dialog = AlertDialog.Builder(context)
            .setView(view)
            .create()
        with(view) {
            formatInput(view)
            save_b.setOnClickListener {
                if (!add_data_et.text.isNullOrEmpty()) {
                    add(Teacher(0, add_data_et.text.toString()))
                    dialog.dismiss()
                }
            }
            cancel_b.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun getLayoutId() = R.layout.fragment_item_setting
}
