package com.oneightwo.schedule.schedule.menu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : BaseFragment() {

    private val dayOfWeek by lazy { arguments?.getInt(DATE_OF_WEEK) ?: 0 }

    companion object {
        const val DATE_OF_WEEK = "dayOfWeek"

        fun newInstance(dayOfWeek: Int): ScheduleFragment{
            val fragment = ScheduleFragment()
            fragment.arguments = Bundle().apply {
                putInt(DATE_OF_WEEK, dayOfWeek - 1)
            }
            return fragment
        }
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(ScheduleViewModel::class.java)
    }

    private fun initViewPager(week: Int) {
        val adapter = DaysViewPagerAdapter(week, context ?: return, childFragmentManager)
        schedule_vp.adapter = adapter
        schedule_vp.currentItem = dayOfWeek
        schedule_tl.setupWithViewPager(schedule_vp)
    }

    private fun initWeekFAB() {
        viewModel.getStateWeekFAB().observe(this, Observer {
            if (it) week_fab.setImageResource(R.drawable.ic_week_1_white_24dp)
            else week_fab.setImageResource(R.drawable.ic_week_2_white_24dp)
        })

        week_fab.setOnClickListener {
            viewModel.changeStateWeekFAB()
            if (viewModel.week == 1) viewModel.week = 2
            else viewModel.week = 1
            initViewPager(viewModel.week)
        }
    }

    private fun initToolBar() {
        (activity as AppCompatActivity).setSupportActionBar(schedule_toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_app_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(context, MenuAddActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initToolBar()
        initViewPager(viewModel.week)
        initWeekFAB()
    }

    override fun getLayoutId() = R.layout.fragment_schedule


}
