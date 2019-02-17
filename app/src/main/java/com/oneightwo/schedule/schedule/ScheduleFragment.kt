package com.oneightwo.schedule.schedule


import android.os.Bundle
import android.view.View
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : BaseFragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun getLayoutId() = R.layout.fragment_schedule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        schedule_vp.adapter = DaysViewPagerAdapter(context ?: return, childFragmentManager)
        schedule_tl.setupWithViewPager(schedule_vp)
    }


}
