package com.oneightwo.schedule.settings


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    private val adapterSettings by lazy { SettingsAdapter(::addSubject) }
    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(SettingsViewModel::class.java)
    }

    private var index = 0

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun getLayoutId() = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        initSpinner()
    }

    private fun addSubject(data: String) {
        viewModel.add(data, index)
        adapterSettings.add(data)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        log(position.toString())
        index = position
        adapterSettings.indexMenu = index
        adapterSettings.update(viewModel.getAllData(index))
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            context ?: return,
            R.array.menu_array,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                choice_s.adapter = adapter
            }
        choice_s.onItemSelectedListener = this
    }

    private fun initRecyclerView(view: View) {
        set_subjects_rv.layoutManager = LinearLayoutManager(view.context)
        set_subjects_rv.adapter = adapterSettings
        adapterSettings.add(viewModel.getAllData(index))
        //set_subjects_rv.itemAnimator = null
    }
}
