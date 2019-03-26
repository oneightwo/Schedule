package com.oneightwo.schedule.settings.fragment

import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.settings.adapter.SubjectAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.SubjectViewModel

class SubjectFragment : BaseSettingFragment<Subject>() {

    companion object {
        fun newInstance() = SubjectFragment()
    }

    override val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(SubjectViewModel::class.java)
    }


    override val adapterItemSettings by lazy {
        SubjectAdapter(
            viewModel::addDataDeletions,
            viewModel::removeDataDeletions,
            viewModel::listDataDeletions,
            viewModel::changeStateFAB,
            viewModel::stateFAB
        )
    }

    override fun getLayoutId() = R.layout.fragment_item_setting
}
