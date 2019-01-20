package com.oneightwo.schedule.schedule


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_day.*

class DayFragment : BaseFragment() {

    private val adapterDays by lazy { DaysAdapter() }

    companion object {
        fun newInstance() = DayFragment()
    }

    override fun getLayoutId() = R.layout.fragment_day

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        subjects_rv.layoutManager = LinearLayoutManager(view.context)
        subjects_rv.adapter = adapterDays
    }
}
