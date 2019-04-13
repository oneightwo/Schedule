package com.oneightwo.schedule.dialog_add.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_dialog_list_add.view.*

class AddListDialogAdapter(
    private val setData: (String) -> Unit
) : BaseAdapter<String, AddListDialogAdapter.AddListDialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddListDialogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_list_add, parent, false)
        return AddListDialogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddListDialogViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddListDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                hint_tv.text = getItemData(position)

                item_hint_rl.setOnClickListener {
                    setData(getItemData(position))
                }
            }
        }
    }
}