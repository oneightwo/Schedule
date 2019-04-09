package com.oneightwo.schedule.menu_аdd

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import com.oneightwo.schedule.tools.IC_ITEM_ADD_MENU
import com.oneightwo.schedule.tools.ITEM_ADD_MENU
import kotlinx.android.synthetic.main.item_menu_add.view.*

class MenuAddAdapter(
    private val clicked: (Int) -> Unit
) : BaseAdapter<String, MenuAddAdapter.MenuAddViewHolder>() {

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
                if (!ITEM_ADD_MENU[position].equals(getItemData(position))) {
                    item_menu_add_tv.setTextColor(Color.BLACK)
                } else {
                    item_menu_add_tv.setTextColor(resources.getColor(R.color.colorGray))
                }

                icon_iv.setImageResource(IC_ITEM_ADD_MENU[position])

                item_ll.setOnClickListener {
                    clicked(position)
                }
            }
        }
    }


}