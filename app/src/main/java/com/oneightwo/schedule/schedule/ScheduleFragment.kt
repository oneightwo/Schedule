package com.oneightwo.schedule.schedule

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
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
        schedule_vp.adapter = DaysViewPagerAdapter(context ?: return, childFragmentManager)
        schedule_tl.setupWithViewPager(schedule_vp)
        initMainFAB()
        initSettingFAB()
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

    private fun initMainFAB() {
        if (viewModel.week == 1) {
            main_fab.setImageResource(R.drawable.ic_week_1_white_24dp)
        } else {
            main_fab.setImageResource(R.drawable.ic_week_2_white_24dp)
        }
        if (viewModel.isSetting) {
            setting_fab.show()
        } else {
            setting_fab.hide()
        }

        main_fab.setOnClickListener {
            if (viewModel.week == 1) {
                main_fab.hide()
                main_fab.setImageResource(R.drawable.ic_week_2_white_24dp)
                main_fab.show()
                viewModel.week = 2
            } else {
                main_fab.hide()
                main_fab.setImageResource(R.drawable.ic_week_1_white_24dp)
                main_fab.show()
                viewModel.week = 1
            }
        }

        main_fab.setOnLongClickListener {

            if (!setting_fab.isOrWillBeShown) {
                setting_fab.show()
                viewModel.isSetting = true
            } else {
                setting_fab.hide()
                viewModel.isSetting = false
            }
            return@setOnLongClickListener true
        }
    }

    private fun initSettingFAB() {
        setting_fab.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_schedule, null)
            val dialog = AlertDialog.Builder(context)
                .setView(view)
                .create()
            initSpinners(view)
            with(view) {
                //                save_b.setOnClickListener {
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
