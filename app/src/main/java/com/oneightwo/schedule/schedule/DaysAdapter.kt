package com.oneightwo.schedule.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R

class DaysAdapter : RecyclerView.Adapter<DaysAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 5

    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}