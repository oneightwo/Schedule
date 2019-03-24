package com.oneightwo.schedule.schedule.menu

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.database.schedule.Schedule
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.dialog_schedule.view.*
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : BaseFragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(ScheduleViewModel::class.java)
    }

    override fun getLayoutId() = R.layout.fragment_schedule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DaysViewPagerAdapter(context ?: return, childFragmentManager)
        schedule_vp.adapter = adapter
        schedule_tl.setupWithViewPager(schedule_vp)

        initMainFAB()
        viewModel.getStateMainFAB().observe(this, Observer {
            clickMainFAB(it)
        })
        viewModel.getStateSettingFAB().observe(this, Observer {
            initSettingFAB(it)
        })
    }

    private fun initSpinners(view: View) {
        viewModel.getData(
            callback1 = {
                val adapter =
                    ArrayAdapter(context ?: return@getData, android.R.layout.simple_spinner_item, it.map { it.time })
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                view.time_s.adapter = adapter
            }, callback2 = {
                val adapter =
                    ArrayAdapter(context ?: return@getData, android.R.layout.simple_spinner_item, it.map { it.subject })
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                view.subject_s.adapter = adapter
            }, callback3 = {
                val adapter =
                    ArrayAdapter(context ?: return@getData, android.R.layout.simple_spinner_item, it.map { it.teacher })
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                view.teacher_s.adapter = adapter
            }, callback4 = {
                val adapter =
                    ArrayAdapter(context ?: return@getData, android.R.layout.simple_spinner_item, it.map { it.cabinet })
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                view.cabinet_s.adapter = adapter
            }
        )


    }

    private fun clickMainFAB(state: Boolean) {
        if (state) main_fab.setImageResource(R.drawable.ic_week_1_white_24dp)
        else main_fab.setImageResource(R.drawable.ic_week_2_white_24dp)
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

    private fun initSettingFAB(state: Boolean) {

        if (state) setting_fab.show()
        else setting_fab.hide()

        setting_fab.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_schedule, null)
            val dialog = AlertDialog.Builder(context)
                .setView(view)
                .create()
            initSpinners(view)
            with(view) {
                save_b.setOnClickListener {
                    log("${day_s.selectedItemPosition}")
                    viewModel.addDay(
                        Schedule(
                            0,
                            week_s.selectedItem.toString().toInt(),
//                            1,
                            day_s.selectedItemPosition,
                            time_s.selectedItem.toString(),
                            subject_s.selectedItem.toString(),
                            cabinet_s.selectedItem.toString(),
                            teacher_s.selectedItem.toString(),
                            "l"
                        )
                    )

                    setting_fab.hide()
                }
                cancel_b.setOnClickListener {
                    setting_fab.hide()
                    dialog.dismiss()
                }
// save_b.setOnClickListener {
//                    if (!add_data_et.text.isNullOrEmpty()) {
//                        dialog.dismiss()
//                    }
//                }
//                cancel_b.setOnClickListener {
//                    dialog.dismiss()
//                }
            }
            dialog.show()
        }
    }

}
