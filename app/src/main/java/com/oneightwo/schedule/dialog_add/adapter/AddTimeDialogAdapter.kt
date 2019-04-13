package com.oneightwo.schedule.dialog_add.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_dialog_add.view.*

class AddTimeDialogAdapter(
    private val setData: (String) -> Unit
) : BaseAdapter<String, AddTimeDialogAdapter.AddTimeDialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddTimeDialogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_list_add, parent, false)
        return AddTimeDialogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddTimeDialogViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddTimeDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                hint_tv.text = getItemData(position)

                hint_tv.setOnClickListener {
                    setData(getItemData(position))
                }
            }
        }

    }
}