package com.oneightwo.schedule.menu_аdd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_menu_add.view.*

class MenuAddAdapter(
    private val clicked: (Int) -> Unit
) : BaseAdapter<String, MenuAddAdapter.MenuAddViewHolder>() {

    private val listIcon = arrayOf<Int>(
        R.drawable.ic_today_black_24dp,
        R.drawable.ic_sync_black_24dp,
        R.drawable.ic_access_time_black_24dp,
        R.drawable.ic_access_time_black_24dp,
        R.drawable.ic_subject_black_24dp,
        R.drawable.ic_school_black_24dp,
        R.drawable.ic_person_black_24dp,
        R.drawable.ic_import_contacts_black_24dp
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAddViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_add, parent, false)
        return MenuAddViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuAddViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class MenuAddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

            with(itemView) {
                item_menu_add_tv.text = getItemData(position)
                icon_iv.setImageResource(listIcon[position])

                item_ll.setOnClickListener {
                    clicked(position)
                }
            }
        }
    }


}