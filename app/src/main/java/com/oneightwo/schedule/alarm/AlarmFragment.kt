package com.oneightwo.schedule.alarm


import android.os.Bundle
import android.view.View
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment


class AlarmFragment : BaseFragment() {

    companion object {
        fun newInstance() = AlarmFragment()
    }

    override fun getLayoutId() = R.layout.fragment_alarm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
