package com.oneightwo.schedule.settings.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.settings.adapter.TimeAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.TimeViewModel
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.fragment_item_setting.*

class TimeFragment : BaseSettingFragment<Time>(1) {

    companion object {
        fun newInstance() = TimeFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(TimeViewModel::class.java)
    }

    private val adapterItemSettings by lazy {
        TimeAdapter(
            ::addPosition,
            ::removePosition,
            ::getPositions,
            ::setLongClick,
            ::isLongClick
        )
    }

    private fun initRecyclerView(data: List<Time>) {
        set_subjects_rv.layoutManager = LinearLayoutManager(view?.context)
        set_subjects_rv.adapter = adapterItemSettings
        adapterItemSettings.update(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modeFAB()
        viewModel.getAll {
            initRecyclerView(it)
        }
    }

    override fun clickAddFAB(add: (String) -> Unit) {
        add_fab.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_add, null)
            val dialog = AlertDialog.Builder(context)
                .setView(view)
                .create()
            with(view) {
                formatInput(view)
                save_b.setOnClickListener {
                    if (!add_data_et.text.isNullOrEmpty()) {

                        add(add_data_et.text.toString())
                        dialog.dismiss()
                    }
                }
                cancel_b.setOnClickListener {
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }

    override fun addData(data: String) {
        viewModel.add(Time(0, data)) {
            adapterItemSettings.update(it)
        }
    }

    override fun modeFAB() {
        if (viewModel.isAdd) {
            add_fab.setImageResource(R.drawable.ic_add_white_24dp)
            clickAddFAB(::addData)
        } else {
            add_fab.setImageResource(R.drawable.ic_delete_white_24dp)
            clickDeleteFAB()
        }
    }

    override fun clickDeleteFAB() {
        add_fab.setOnClickListener {
            viewModel.deleteData {
                setLongClick(false)
                adapterItemSettings.update(it)
                modeFAB()
            }
        }
    }

    override fun addPosition(position: Int, data: Time) {
        with(viewModel) {
            positionStorage.add(position)
            deleteData.add(data)
        }
    }

    override fun removePosition(position: Int, data: Time) {
        viewModel.positionStorage.remove(position)
        viewModel.deleteData.remove(data)
        if (viewModel.positionStorage.isEmpty())
            setLongClick(false)
    }

    override fun getPositions() = ArrayList<Int>(viewModel.positionStorage)

    override fun setLongClick(isClick: Boolean) {
        if (!isClick) {
            viewModel.clear()
            adapterItemSettings.notifyDataSetChanged()
        } else {
            viewModel.isAdd = !isClick
        }
        modeFAB()
    }



    override fun isLongClick() = !viewModel.isAdd

    override fun getLayoutId() = R.layout.fragment_item_setting
}
