package com.oneightwo.schedule.settings.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.settings.viewModel.CabinetViewModel


class CabinetFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(CabinetViewModel::class.java)
    }

    companion object {
        fun newInstance() = CabinetFragment()
    }

    override fun getLayoutId() = R.layout.fragment_item_setting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
