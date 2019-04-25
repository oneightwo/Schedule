package com.oneightwo.schedule.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter

class NotesAdapter() : BaseAdapter<String, NotesAdapter.NotesViewHolder>() {

    override fun getItemCount() = 7

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return NotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }
}