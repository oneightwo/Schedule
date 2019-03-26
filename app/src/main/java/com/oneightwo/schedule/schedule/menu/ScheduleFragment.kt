package com.oneightwo.schedule.schedule.menu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : BaseFragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(ScheduleViewModel::class.java)
    }

    private fun initObservers() {
        viewModel.getStateMainFAB().observe(this, Observer {
            if (it) main_fab.setImageResource(R.drawable.ic_week_1_white_24dp)
            else main_fab.setImageResource(R.drawable.ic_week_2_white_24dp)
        })

        viewModel.getStateSettingFAB().observe(this, Observer {
            if (it) setting_fab.show()
            else setting_fab.hide()
        })
    }

    private fun initViewPager() {
        val adapter = DaysViewPagerAdapter(context ?: return, childFragmentManager)
        schedule_vp.adapter = adapter
        schedule_tl.setupWithViewPager(schedule_vp)
    }

    private fun initMainFAB() {
        main_fab.setOnClickListener {
            viewModel.changeStateMainFAB()
        }

        main_fab.setOnLongClickListener {
            viewModel.changeStateSettingFAB()
            return@setOnLongClickListener true
        }
    }

    private fun showDialog() {

    }

    private fun initSettingFAB() {
        setting_fab.setOnClickListener {
            showDialog()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initMainFAB()
        initSettingFAB()
        initObservers()
    }

    override fun getLayoutId() = R.layout.fragment_schedule

}
