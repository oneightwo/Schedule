package com.oneightwo.schedule.menu_аdd.dialog

import android.content.pm.ActivityInfo
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneightwo.schedule.R
import com.oneightwo.schedule.menu_аdd.MenuAddActivity
import com.oneightwo.schedule.menu_аdd.dialog.adapter.AddDialogAdapter
import com.oneightwo.schedule.menu_аdd.dialog.adapter.AddListDialogAdapter
import kotlinx.android.synthetic.main.dialog_day_of_week.view.*


class AddDialog(
    private val context: MenuAddActivity,
    private val getData: (Int) -> List<String>,
    private val setData: (String) -> Unit,
    private val deleteData: (Int) -> Unit,
    private val position: Int
) : androidx.appcompat.app.AlertDialog(context) {

    private val adapterDialog by lazy {
        when (position) {
            0, 1, 7 -> AddListDialogAdapter(
                setData

            )
            else -> AddDialogAdapter(
                setData,
                deleteData
            )
        }
    }

    private var dialog: View? = null

    init {
        when (position) {
            0, 1, 7 -> {
                val view = context.layoutInflater.inflate(R.layout.dialog_add_list, null)
                with(view) {
                    initRecyclerView(view)
                    adapterDialog.add(
                        getData(position)
                    )

                }
                setOnDismissListener {
                    orientation(false)
                }
                setView(view)
                dialog = view
            }
            else -> {
                val view = context.layoutInflater.inflate(R.layout.dialog_day_of_week, null)
                with(view) {
                    initRecyclerView(view)
                    adapterDialog.add(
                        getData(position)
                    )
                    save_b.setOnClickListener {
                        if (!add_data_et.text.isNullOrEmpty()) {
                            setData(add_data_et.text.toString())
                        }
                        dismiss()
                    }
                    cancel_b.setOnClickListener {
                        dismiss()
                    }
                }
                setOnDismissListener {
                    orientation(false)
                }
                setView(view)
            }
        }

    }

    private fun initRecyclerView(view: View) {
        with(view) {
            hint_rv.layoutManager = LinearLayoutManager(context)
            hint_rv.adapter = adapterDialog
        }
    }



    private fun orientation(block: Boolean) {
        context.requestedOrientation = if (block) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }
    }

    override fun show() {
        orientation(true)
        super.show()
    }

}