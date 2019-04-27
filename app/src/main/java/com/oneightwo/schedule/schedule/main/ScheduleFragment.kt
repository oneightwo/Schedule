package com.oneightwo.schedule.schedule.main

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
import com.oneightwo.schedule.schedule.menu_аdd.MenuAddActivity
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : BaseFragment() {

    private val dayOfWeek by lazy {
        arguments?.getInt(DATE_OF_WEEK) ?: 0
    }

    private val numberWeek by lazy {
        arguments?.getInt(NUMBER_WEEK)
    }

    companion object {
        const val DATE_OF_WEEK = "dayOfWeek"
        const val NUMBER_WEEK = "numberWeek"

        fun newInstance(numberWeek: Int, dayOfWeek: Int): ScheduleFragment {
            val fragment = ScheduleFragment()
            fragment.arguments = Bundle().apply {
                putInt(DATE_OF_WEEK, dayOfWeek - 1)
                putInt(NUMBER_WEEK, numberWeek)
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
            if (it == 1) week_fab.setImageResource(R.drawable.ic_week_1_white_24dp)
            else week_fab.setImageResource(R.drawable.ic_week_2_white_24dp)
        })

        week_fab.setOnClickListener {
            viewModel.changeStateWeekFAB()
            initViewPager(viewModel.getStateWeekFAB().value!!)
        }
    }

    private fun initToolbar() {
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
        log("numberWeek -> $numberWeek")
        viewModel.getStateWeekFAB().value = numberWeek
        initToolbar()
        initViewPager(viewModel.getStateWeekFAB().value!!)
        initWeekFAB()
    }

    override fun getLayoutId() = R.layout.fragment_schedule


}
