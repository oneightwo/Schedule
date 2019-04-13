package com.oneightwo.schedule.add_time

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import com.oneightwo.schedule.tools.IC_NUMBER_BELL
import kotlinx.android.synthetic.main.item_add_time.view.*

class AddTimeAdapter(
    private val setStartTime: (Int) -> Unit,
    private val setEndTime: (Int) -> Unit,
    private val setNumberTime: (Int) -> Unit
) : BaseAdapter<TimeTemporaryStorage, AddTimeAdapter.AddTimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddTimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_add_time, parent, false)
        return AddTimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddTimeViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                if (getItemData(position).number != null) ic_number_iv.setImageResource(
                    IC_NUMBER_BELL[getItemData(
                        position
                    ).number!!]
                )
                if (getItemData(position).start != null) {
                    start_time_tv.setTextColor(Color.BLACK)
                    start_time_tv.text = getItemData(position).start
                }

                if (getItemData(position).end != null) end_time_tv.text = getItemData(position).end

                start_time_tv.setOnClickListener {
                    setStartTime(position)
                }

                end_time_tv.setOnClickListener {
                    setEndTime(position)
                }

                ic_number_iv.setOnClickListener {
                    setNumberTime(position)
                }
            }
        }
    }
}