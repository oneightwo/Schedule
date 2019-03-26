package com.oneightwo.schedule.settings.fragment


import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.settings.adapter.TimeAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.TimeViewModel
import kotlinx.android.synthetic.main.dialog_add_time.view.*

class TimeFragment : BaseSettingFragment<Time>() {

    companion object {
        fun newInstance() = TimeFragment()
    }

    override val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(TimeViewModel::class.java)
    }

    override val adapterItemSettings by lazy {
        TimeAdapter(
            viewModel::addDataDeletions,
            viewModel::removeDataDeletions,
            viewModel::listDataDeletions,
            viewModel::changeStateFAB,
            viewModel::stateFAB
        )
    }

    override fun initDialog(add: (Time) -> Unit) {
        val view = layoutInflater.inflate(R.layout.dialog_add_time, null)
        val dialog = AlertDialog.Builder(context)
            .setView(view)
            .create()
        with(view) {
            formatInput(view)
            save_b.setOnClickListener {
                if (!add_first_time_et.text.isNullOrEmpty() && !add_second_time_et.text.isNullOrEmpty()) {
                    add(Time(0, add_first_time_et.text.toString(), add_second_time_et.text.toString()))
                    dialog.dismiss()
                }
            }
            cancel_b.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun getLayoutId() = R.layout.fragment_item_setting
}
