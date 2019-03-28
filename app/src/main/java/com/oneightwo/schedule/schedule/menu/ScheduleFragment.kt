package com.oneightwo.schedule.schedule.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : BaseFragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(ScheduleViewModel::class.java)
    }

    private fun initViewPager() {
        val adapter = DaysViewPagerAdapter(context ?: return, childFragmentManager)
        schedule_vp.adapter = adapter
        schedule_tl.setupWithViewPager(schedule_vp)
    }

    private fun initWeekFAB() {
        viewModel.getStateWeekFAB().observe(this, Observer {
            if (it) week_fab.setImageResource(R.drawable.ic_week_1_white_24dp)
            else week_fab.setImageResource(R.drawable.ic_week_2_white_24dp)
        })

        week_fab.setOnClickListener {
            viewModel.changeStateWeekFAB()
        }

        week_fab.setOnLongClickListener {
            viewModel.changeStateAddFAB()
            return@setOnLongClickListener true
        }
    }

    private fun initAddFAB() {
        viewModel.getStateAddFAB().observe(this, Observer {
            if (it) add_fab.show()
            else add_fab.hide()
        })

        add_fab.setOnClickListener {
//            val v = ViewAnimationUtils.createCircularReveal(add_fab, add_fab.scrollX, add_fab.scrollY,  0F,  this.view?.width!!.toFloat())
//            v.interpolator = AccelerateDecelerateInterpolator()
//            v.duration = 3000
//            v.start()
//            val nextFrag = MenuAddFragment()
//            activity!!.supportFragmentManager.beginTransaction()
//                .replace(com.oneightwo.schedule.R.id.container, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit()
            val intent = Intent(context, MenuAddActivity::class.java)
            startActivity(intent)
            viewModel.changeStateAddFAB()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initWeekFAB()
        initAddFAB()
    }

    override fun getLayoutId() = R.layout.fragment_schedule

}
