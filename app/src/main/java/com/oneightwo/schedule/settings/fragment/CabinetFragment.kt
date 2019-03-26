package com.oneightwo.schedule.settings.fragment

import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.settings.adapter.CabinetAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.CabinetViewModel

class CabinetFragment : BaseSettingFragment<Cabinet>() {

    companion object {
        fun newInstance() = CabinetFragment()
    }

    override val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(CabinetViewModel::class.java)
    }

    override val adapterItemSettings by lazy {
        CabinetAdapter(
            viewModel::addDataDeletions,
            viewModel::removeDataDeletions,
            viewModel::listDataDeletions,
            viewModel::changeStateFAB,
            viewModel::stateFAB
        )
    }

    override fun getLayoutId() = R.layout.fragment_item_setting
}
