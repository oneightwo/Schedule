package com.oneightwo.schedule.settings.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_settings.view.*

abstract class BaseSettingAdapter<T>(
    private val addPosition: (Int, T) -> Unit,
    private val removePosition: (Int, T) -> Unit,
    private val getPositions: () -> ArrayList<Int>,
    private val setLongClick: (Boolean) -> Unit,
    private val isLongClick: () -> Boolean
) :
    BaseAdapter<T, BaseSettingAdapter<T>.BaseSettingsViewHolder>() {

    abstract fun getText(item: T): String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSettingAdapter<T>.BaseSettingsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_settings, parent, false)
        return BaseSettingsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BaseSettingAdapter<T>.BaseSettingsViewHolder, position: Int) {
        holder.bind(position)
    }

    private fun init(itemView: View, position: Int) {
        with(itemView) {
            input_subject_tv.visibility = View.VISIBLE
            input_subject_tv.text = getText(getItemData(position))
            if (!isLongClick()) {
                check_box_iv.visibility = View.GONE
            } else {
                check_box_iv.visibility = View.VISIBLE
                check_box_iv.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)
                for (i in getPositions()) {
                    if (position == i)
                        check_box_iv.setImageResource(R.drawable.ic_check_box_black_24dp)
                }
            }
        }
    }

    inner class BaseSettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                init(itemView, position)

                item_rl.setOnLongClickListener {
                    setLongClick(true)
                    notifyDataSetChanged()
                    addPosition(position, getItemData(position))
                    check_box_iv.setImageResource(R.drawable.ic_check_box_black_24dp)
                    return@setOnLongClickListener true
                }

//                if (isLongClick()) {
                item_rl.setOnClickListener {
                    if (isLongClick()) {
                        if (getPositions().contains(position)) {
                            removePosition(position, getItemData(position))
                            check_box_iv.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)
                            if (getPositions().isEmpty()) {
                                setLongClick(false)
                            }
                        } else {
                            addPosition(position, getItemData(position))
                            check_box_iv.setImageResource(R.drawable.ic_check_box_black_24dp)

                        }
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }
}