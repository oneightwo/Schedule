package com.oneightwo.schedule.schedule.day

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.schedule.menu_аdd.MenuAddActivity
import com.oneightwo.schedule.schedule.tools.FormatOutput
import com.oneightwo.schedule.tools.TYPES_CLASSES
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.fragment_day.*

class DayFragment : BaseFragment() {

    private val numberPage by lazy { arguments?.getInt(ARG_NUMBER) ?: 0 }
    private val week by lazy { arguments?.getInt(WEEK) ?: 0 }

    companion object {
        const val ARG_NUMBER = "number"
        const val WEEK = "week"

        fun newInstance(numberPage: Int, week: Int): DayFragment {
            val fragment = DayFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_NUMBER, numberPage)
                putInt(WEEK, week)
            }
            return fragment
        }
    }

    private fun getDataDay(schedule: List<Schedule>): List<FormatOutput> {
        log("schedule = $schedule")
        val result = arrayListOf<FormatOutput>()
        val data = schedule.filter { it.day == numberPage && it.week == week }
            .sortedBy { "${it.time.substringBefore(':')}.${it.time.substringAfter(':').substringBefore(' ')}".toDouble() }
        viewModel.storage = data
        log("data = $data")
        for (i in data) {
            log("${i.type}")
            result.add(
                FormatOutput(
                    if (i.type != null) TYPES_CLASSES[i.type] else null,
                    i.subject,
                    i.time,
                    i.cabinet,
                    i.teacher
                )
            )
        }
        log("result = $result")
        return result
    }

    private val adapterDay by lazy { DayAdapter(::onClick) }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(DayViewModel::class.java)
    }

    private fun initObservers() {
        viewModel.day.observe(this, Observer {
            log("OPAAAAAAAAAAAAAA DAY_FRAGMENT")
            adapterDay.update(getDataDay(it))
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.numberPage = numberPage
        log("$numberPage $week")
        initRecyclerView()
        initObservers()
    }

    override fun getLayoutId() = R.layout.fragment_day

    private fun onClick(position: Int) {
        val item: Schedule = viewModel.storage[position]

        val intent = Intent(context, MenuAddActivity::class.java)
        intent.putExtra(Schedule::class.java.simpleName, item)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        subjects_rv.layoutManager = LinearLayoutManager(context)
        subjects_rv.adapter = adapterDay
    }
}