package com.oneightwo.schedule.settings.fragment

import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.time.TimeStart
import com.oneightwo.schedule.settings.adapter.TimeAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.TimeViewModel

class TimeFragment : BaseSettingFragment<TimeStart>() {

    companion object {
        fun newInstance() = TimeFragment()
    }

    override val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(TimeViewModel::class.java)
    }

    override val adapterItemSettings by lazy {
        TimeAdapter(
            viewModel::addDataDeletions,
            viewModel::removeDataDeletions,
            viewModel::listDataDeletions,
            viewModel::changeStateFAB,
            viewModel::stateFAB
        )
    }

    override fun getLayoutId() = R.layout.fragment_item_setting
}
