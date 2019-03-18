package com.oneightwo.schedule.schedule.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.schedule.day.DayAdapter
import com.oneightwo.schedule.schedule.day.DayViewModel
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.fragment_day.*

class BaseDayFragment : BaseFragment() {

    private val numberPage by lazy { arguments?.getInt(ARG_NUMBER) ?: 0 }

    companion object {
        const val ARG_NUMBER = "number"

        fun newInstance(numberPage: Int): BaseDayFragment {
            val fragment = BaseDayFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_NUMBER, numberPage)
            }
            log(numberPage.toString())
            return fragment
        }
    }

    private val adapterDay by lazy { DayAdapter() }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(DayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.numberPage = numberPage
        initRecyclerView()
        viewModel.day.observe(this, Observer { data ->
            adapterDay.update(data.filter { it.day == numberPage })
        })
    }

    override fun getLayoutId() = R.layout.fragment_day

    private fun initRecyclerView() {
        subjects_rv.layoutManager = LinearLayoutManager(context)
        subjects_rv.adapter = adapterDay
    }
}