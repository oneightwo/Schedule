package com.oneightwo.schedule.settings.fragment


import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.settings.viewModel.TimeViewModel

class TimeFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(TimeViewModel::class.java)
    }

    companion object {
        fun newInstance() = TimeFragment()
    }

    override fun getLayoutId() = R.layout.fragment_item_setting
}
