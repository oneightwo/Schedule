package com.oneightwo.schedule.menu_аdd.dialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_dialog_add.view.*

class AddDialogAdapter(
    private val setData: (String) -> Unit,
    private val deleteData: (String) -> Unit
) : BaseAdapter<String, AddDialogAdapter.AddDialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddDialogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_add, parent, false)
        return AddDialogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddDialogViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                hint_tv.text = getItemData(position)

                hint_tv.setOnClickListener {
                    setData(getItemData(position))
                }

                delete_hint_iv.setOnClickListener {
                    deleteData(getItemData(position))
                }
            }
        }
    }
}