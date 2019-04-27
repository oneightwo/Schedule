package com.oneightwo.schedule.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, ViewHolder: RecyclerView.ViewHolder> : RecyclerView.Adapter<ViewHolder>() {

    private val data = arrayListOf<T>()

    fun getItemData(position: Int) = data[position]

    fun add(data: List<T>) {
        val startPosition = this.data.size
        this.data.addAll(data)
        notifyItemRangeInserted(startPosition, data.size)
    }

    fun add(data: T) {
        this.data.add(data)
        notifyItemInserted(this.data.size - 1)
    }

    fun remove(data: T) {
        val index = this.data.indexOf(data)
        this.data.remove(data)
        notifyItemRemoved(index)
    }

    fun update(data: T) {
        clear()
        add(data)
    }

    fun update(data: List<T>) {
        clear()
        add(data)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun size() = if (data.isEmpty()) 0 else data.size

    override fun getItemCount() = data.size
}