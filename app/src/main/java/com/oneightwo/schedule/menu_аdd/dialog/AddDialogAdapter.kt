package com.oneightwo.schedule.menu_аdd.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_dialog_add_hint.view.*

class AddDialogAdapter : BaseAdapter<String, AddDialogAdapter.AddDialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddDialogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_add_hint, parent, false)
        return AddDialogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddDialogViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                hint_tv.text = getItemData(position)
            }
        }
    }
}