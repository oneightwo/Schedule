package com.oneightwo.schedule.settings.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.cabinet.CabinetDao
import com.oneightwo.schedule.settings.adapter.CabinetAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.CabinetViewModel
import kotlinx.android.synthetic.main.dialog_add.view.*


class CabinetFragment : BaseSettingFragment<Cabinet, CabinetDao>() {

    companion object {
        fun newInstance() = CabinetFragment()
    }


    override val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(CabinetViewModel::class.java)
    }

    //    private val viewModel by lazy {
//        ViewModelProviders.of(this)
//            .get(CabinetViewModel::class.java)
//    }

    override val adapterItemSettings by lazy {
        CabinetAdapter(
//            ::addPosition,
//            ::removePosition,
//            ::getPositions,
//            ::setLongClick,
//            ::isLongClick
        )
    }

    override fun initDialog(add: (Cabinet) -> Unit) {
        val view = layoutInflater.inflate(R.layout.dialog_add, null)
        val dialog = AlertDialog.Builder(context)
            .setView(view)
            .create()
        with(view) {
//            formatInput(view)
            save_b.setOnClickListener {
                if (!add_data_et.text.isNullOrEmpty()) {

                    add(Cabinet(0, add_data_et.text.toString()))
                    dialog.dismiss()
                }
            }
            cancel_b.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

//    private val adapterItemSettings by lazy {
//        CabinetAdapter(
//            ::addPosition,
//            ::removePosition,
//            ::getPositions,
//            ::setLongClick,
//            ::isLongClick
//        )
//    }

    override fun getLayoutId() = R.layout.fragment_item_setting

//    private fun initRecyclerView(data: List<Cabinet>) {
//        set_subjects_rv.layoutManager = LinearLayoutManager(view?.context)
//        set_subjects_rv.adapter = adapterItemSettings
//        adapterItemSettings.update(data)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(this, Observer {
            initRecyclerView(it)
        })
        initFAB()

//        modeFAB()
//        viewModel.getAll {
//            initRecyclerView(it)
//        }
    }

//    override fun clickAddFAB(add: (String) -> Unit) {
//        add_fab.setOnClickListener {
//            val view = layoutInflater.inflate(R.layout.dialog_add, null)
//            val dialog = AlertDialog.Builder(context)
//                .setView(view)
//                .create()
//            with(view) {
////                formatInput(view)
//                save_b.setOnClickListener {
//                    if (!add_data_et.text.isNullOrEmpty()) {
//
//                        add(add_data_et.text.toString())
//                        dialog.dismiss()
//                    }
//                }
//                cancel_b.setOnClickListener {
//                    dialog.dismiss()
//                }
//            }
//            dialog.show()
//        }
//    }

//    override fun addData(data: String) {
//        viewModel.add(Cabinet(0, data)) {
//            adapterItemSettings.update(it)
//        }
//    }
//
//    override fun modeFAB() {
//        if (viewModel.isAdd) {
//            add_fab.setImageResource(R.drawable.ic_add_white_24dp)
//            clickAddFAB(::addData)
//        } else {
//            add_fab.setImageResource(R.drawable.ic_delete_white_24dp)
//            clickDeleteFAB()
//        }
//    }
//
//    override fun clickDeleteFAB() {
//        add_fab.setOnClickListener {
//            viewModel.deleteData {
//                setLongClick(false)
//                adapterItemSettings.update(it)
//                modeFAB()
//            }
//        }
//    }
//
//    override fun addPosition(position: Int, data: Cabinet) {
//        with(viewModel) {
//            positionStorage.add(position)
//            deleteData.add(data)
//        }
//    }
//
//    override fun removePosition(position: Int, data: Cabinet) {
//        viewModel.positionStorage.remove(position)
//        viewModel.deleteData.remove(data)
//        if (viewModel.positionStorage.isEmpty())
//            setLongClick(false)
//    }
//
//    override fun getPositions() = ArrayList<Int>(viewModel.positionStorage)
//
//    override fun setLongClick(isClick: Boolean) {
//        if (!isClick) {
//            viewModel.clear()
//            adapterItemSettings.notifyDataSetChanged()
//        } else {
//            viewModel.isAdd = !isClick
//        }
//        modeFAB()
//    }
//
//    override fun isLongClick() = !viewModel.isAdd
}
