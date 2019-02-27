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
      //  initMainFAB()
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(getLayoutId(), container, false)
//        return view
//
//    }

//    private fun initMainFAB() {
//        main_fab.setOnClickListener{
//            if (main_fab.isOrWillBeShown)
//            main_fab.hide()
//            main_fab.setImageResource(R.drawable.ic_week_2_white_24dp)
//            main_fab.show()
//
//        }
//
//        main_fab.setOnLongClickListener {
//            if (!setting_fab.isOrWillBeShown) {
//                setting_fab.show()
//            }
//            return@setOnLongClickListener true
//        }
//    }

    private fun initRecyclerView(view: View) {
        subjects_rv.layoutManager = LinearLayoutManager(view.context)
        subjects_rv.adapter = adapterDays
    }
}
