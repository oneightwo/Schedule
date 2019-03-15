package com.oneightwo.schedule.schedule.day

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.database.schedule.Schedule
import kotlinx.android.synthetic.main.fragment_day.*

class DayFragment : BaseFragment() {

    private val adapterDay by lazy { DayAdapter() }
    private val bookItems: MutableList<Schedule> = ArrayList()

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(DayViewModel::class.java)
    }

    companion object {
        private const val ARGUMENT_PAGE_NUMBER = "arg_page_number"

        fun newInstance(page: Int): Fragment {
            val dayFragment = DayFragment()
            val arguments = Bundle()
            arguments.putInt(ARGUMENT_PAGE_NUMBER, page)
            dayFragment.arguments = arguments
            return dayFragment
        }
    }

    override fun getLayoutId() = R.layout.fragment_day

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.numberPage = arguments?.getInt(ARGUMENT_PAGE_NUMBER)
        viewModel.day.observe(this, Observer<List<Schedule>> {
            if (bookItems.isNotEmpty())
                bookItems.clear()
            bookItems.addAll(it!!)
        })
//        viewModel.getAll {
//            initRecyclerView(it)
//            log(it.toString())
////            text.text = it.toString()
//        }
    }


    private fun initRecyclerView(data: List<Schedule>) {
        subjects_rv.layoutManager = LinearLayoutManager(view?.context)
        subjects_rv.adapter = adapterDay
        adapterDay.update(data)
    }
}
