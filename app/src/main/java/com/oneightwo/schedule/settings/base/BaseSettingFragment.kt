package com.oneightwo.schedule.settings.base

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType.*
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.database.time.TimeEnd
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.dialog_add_time.view.*
import kotlinx.android.synthetic.main.fragment_item_setting.*

abstract class BaseSettingFragment<T> : BaseFragment() {

    abstract val viewModel: BaseSettingViewModel<T>
    abstract val adapterItemSettings: BaseSettingAdapter<T>

    private fun initFAB() {
        add_fab.setOnClickListener {
            with(viewModel) {
                if (stateFAB()) {
                    initDialog {
                        add(it)
                    }
                } else {
                    delete(listDataDeletions())
                }
            }
        }
    }

    private fun initRecyclerView() {
        set_subjects_rv.layoutManager = LinearLayoutManager(view?.context)
        set_subjects_rv.adapter = adapterItemSettings
    }

    @Suppress("UNCHECKED_CAST")
    private fun initDialog(add: (T) -> Unit) {
        when {
            viewModel.cls.isAssignableFrom(TimeEnd::class.java) -> {
                val view = layoutInflater.inflate(R.layout.dialog_add_time, null)
                val dialog = AlertDialog.Builder(context)
                    .setView(view)
                    .create()
                formatInput(view)
                with(view) {
                    save_time_b.setOnClickListener {
                        if (!add_first_time_et.text.isNullOrEmpty() && !add_second_time_et.text.isNullOrEmpty()) {
                            add(TimeEnd(0, add_first_time_et.text.toString()) as T)
                            dialog.dismiss()
                        }
                    }
                    cancel_time_b.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                }
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.dialog_add, null)
                val dialog = AlertDialog.Builder(context)
                    .setView(view)
                    .create()
                formatInput(view)
                with(view) {
                    save_b.setOnClickListener {
                        if (!add_data_et.text.isNullOrEmpty()) {
                            when {
                                viewModel.cls.isAssignableFrom(Subject::class.java) -> add(
                                    Subject(
                                        0,
                                        add_data_et.text.toString()
                                    ) as T
                                )
                                viewModel.cls.isAssignableFrom(Cabinet::class.java) -> add(
                                    Cabinet(
                                        0,
                                        add_data_et.text.toString()
                                    ) as T
                                )
                                viewModel.cls.isAssignableFrom(Teacher::class.java) -> add(
                                    Teacher(
                                        0,
                                        add_data_et.text.toString()
                                    ) as T
                                )
                            }
                            dialog.dismiss()
                        }
                    }
                    cancel_b.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.data.observe(this, Observer {
            adapterItemSettings.update(it)
        })

        viewModel.getStatusFAB().observe(this, Observer {
            if (it) {
                add_fab.setImageResource(R.drawable.ic_add_white_24dp)
            } else {
                add_fab.setImageResource(R.drawable.ic_delete_white_24dp)
            }
            adapterItemSettings.notifyDataSetChanged()
        })

        viewModel.getDataDeletion().observe(this, Observer {
            if (it.isEmpty()) {
                viewModel.changeStateFAB(true)
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initFAB()
        initObservers()
    }

    private fun formatInput(view: View) {
        viewModel.cls
        with(viewModel.cls) {
            when {
                isAssignableFrom(Cabinet::class.java) -> {
                    view.add_hint_data_tv.hint = context?.getString(R.string.example_cabinet)
                    view.add_data_et.inputType = TYPE_CLASS_TEXT + TYPE_TEXT_FLAG_CAP_SENTENCES
                }
                isAssignableFrom(TimeEnd::class.java) -> {
                    view.add_first_time_et.inputType = TYPE_CLASS_DATETIME
                    view.add_second_time_et.inputType = TYPE_CLASS_DATETIME
                }
                isAssignableFrom(Teacher::class.java) -> {
                    view.add_hint_data_tv.hint = context?.getString(R.string.example_teacher)
                    view.add_data_et.inputType = TYPE_TEXT_VARIATION_PERSON_NAME + TYPE_TEXT_FLAG_CAP_WORDS
                }
                isAssignableFrom(Subject::class.java) -> {
                    view.add_hint_data_tv.hint = context?.getString(R.string.example_subject)
                    view.add_data_et.inputType = TYPE_CLASS_TEXT + TYPE_TEXT_FLAG_CAP_SENTENCES
                }
            }
        }
    }
}