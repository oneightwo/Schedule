package com.oneightwo.schedule.settings.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.settings.adapter.SubjectAdapter
import com.oneightwo.schedule.settings.base.BaseSettingFragment
import com.oneightwo.schedule.settings.viewModel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_item_setting.*

class SubjectFragment : BaseSettingFragment<Subject>(0) {

    companion object {
        fun newInstance() = SubjectFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(SubjectViewModel::class.java)
    }

    private val adapterItemSettings by lazy {
        SubjectAdapter(
            ::addPosition,
            ::removePosition,
            ::getPositions,
            ::setLongClick,
            ::isLongClick
        )
    }

    private fun initRecyclerView(data: List<Subject>) {
        set_subjects_rv.layoutManager = LinearLayoutManager(view?.context)
        set_subjects_rv.adapter = adapterItemSettings
        adapterItemSettings.update(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //LiveData
//        viewModel.getAll().observe(this, Observer {
//            initRecyclerView(it)
//        })
//        add_fab.hide()
//        add_fab.show()
        modeFAB()
        viewModel.getAll {
            initRecyclerView(it)
        }
    }

    override fun addData(data: String) {
        viewModel.add(Subject(0, data)) {
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

    override fun addPosition(position: Int, data: Subject) {
        with(viewModel) {
            positionStorage.add(position)
            deleteData.add(data)
        }
    }

    override fun removePosition(position: Int, data: Subject) {
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

//    private fun addData(data: String) {
//        viewModel.add(Subject(0, data)) {
//            adapterItemSettings.update(it)
//        }
//    }
//
//    private fun modeFAB() {
//        if (viewModel.isAdd) {
//            add_fab.setImageResource(R.drawable.ic_add_white_24dp)
//            clickAddFAB(::addData)
//        } else {
//            add_fab.setImageResource(R.drawable.ic_delete_white_24dp)
//            clickDeleteFAB()
//        }
//    }
//
//    private fun clickDeleteFAB() {
//        add_fab.setOnClickListener {
//            viewModel.deleteData {
//                setLongClick(false)
//                adapterItemSettings.update(it)
//                modeFAB()
//            }
//        }
//    }
//
//    private fun addPosition(position: Int, subject: Subject) {
//        with(viewModel) {
//            positionStorage.add(position)
//            deleteData.add(subject)
//        }
//    }
//
//    private fun removePosition(position: Int, subject: Subject) {
//        viewModel.positionStorage.remove(position)
//        viewModel.deleteData.remove(subject)
//        if (viewModel.positionStorage.isEmpty())
//            setLongClick(false)
//    }
//
//    private fun setLongClick(isClick: Boolean) {
//        if (!isClick) {
//            viewModel.clear()
//            adapterItemSettings.notifyDataSetChanged()
//        } else {
//            viewModel.isAdd = !isClick
//        }
//        modeFAB()
//    }
//
//    private fun isLongClick() = !viewModel.isAdd
//
//    private fun getPositions() = ArrayList<Int>(viewModel.positionStorage)

    override fun getLayoutId() = R.layout.fragment_item_setting
}
