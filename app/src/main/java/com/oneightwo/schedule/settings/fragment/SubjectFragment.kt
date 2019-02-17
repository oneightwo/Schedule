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

    private val adapterItemSettings by lazy { SubjectAdapter(::deleteData) }

    private fun initRecyclerView(data: List<Subject>) {
        set_subjects_rv.layoutManager = LinearLayoutManager(view?.context)
        set_subjects_rv.adapter = adapterItemSettings
        adapterItemSettings.update(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAll {
            initRecyclerView(it)
        }

        clickFloatingActionButton(::addData)
    }

    private fun addData(data: String) {
        viewModel.add(Subject(0, data)) {
            adapterItemSettings.update(it)
        }
    }

    override fun getLayoutId() = R.layout.fragment_item_setting

    override fun deleteData(data: Subject) {
        viewModel.deleteData(data){
            adapterItemSettings.update(it)
        }
    }

    override fun getAllData() {

    }
}
