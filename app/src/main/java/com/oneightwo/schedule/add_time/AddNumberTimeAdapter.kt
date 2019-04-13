package com.oneightwo.schedule.add_time

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import com.oneightwo.schedule.tools.IC_NUMBER_BELL
import kotlinx.android.synthetic.main.item_dialog_add_time.view.*

class AddNumberTimeAdapter(
    private val setNumberTime: (Int) -> Unit
) : BaseAdapter<Int, AddNumberTimeAdapter.AddNumberTimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddNumberTimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_add_time, parent, false)
        return AddNumberTimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddNumberTimeViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddNumberTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                ic_number_iv.setImageResource(IC_NUMBER_BELL[position])

                ic_number_iv.setOnClickListener {
                    setNumberTime(position)
                }
            }
        }
    }
}