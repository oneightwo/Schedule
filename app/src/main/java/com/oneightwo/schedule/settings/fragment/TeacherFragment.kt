package com.oneightwo.schedule.settings.fragment

import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.settings.adapter.TeacherAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.TeacherViewModel

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

    override fun getLayoutId() = R.layout.fragment_item_setting
}
