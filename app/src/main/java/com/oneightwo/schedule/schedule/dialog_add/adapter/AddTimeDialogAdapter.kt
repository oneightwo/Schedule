package com.oneightwo.schedule.schedule.dialog_add.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import com.oneightwo.schedule.schedule.menu_аdd.TimeBell
import com.oneightwo.schedule.tools.IC_NUMBER_BELL
import kotlinx.android.synthetic.main.item_dialog_time_add.view.*

class AddTimeDialogAdapter(
    private val setData: (String) -> Unit
) : BaseAdapter<TimeBell, AddTimeDialogAdapter.AddTimeDialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddTimeDialogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_time_add, parent, false)
        return AddTimeDialogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddTimeDialogViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddTimeDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
//                ic_number_iv.setImageResource(getItemData(position))
                hint_text_tv.text = getItemData(position).time
//                log("${getItemData(position).number}")
                ic_number_iv.setImageResource(IC_NUMBER_BELL[getItemData(position).number])

                hint_text_tv.setOnClickListener {
                    setData(getItemData(position).time)
                }
            }
        }

    }
}