package com.oneightwo.schedule.settings.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_settings.view.*

abstract class BaseSettingAdapter<T>(
    private val deleteData: (T) -> Unit)://,
//    private val addPosition: (Int) -> Unit,
//    private val removePosition: (Int) -> Unit,
//    private val getPositions: () -> ArrayList<Int>) :
    BaseAdapter<T, BaseSettingAdapter<T>.BaseSettingsViewHolder>() {

    private var isLongClick = false
    private var clickPosition: Int? = null

    abstract fun getText(item: T): String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSettingAdapter<T>.BaseSettingsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_settings, parent, false)
        return BaseSettingsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BaseSettingAdapter<T>.BaseSettingsViewHolder, position: Int) {
        holder.bind(position)
    }

    fun visibleDone(itemView: View, position: Int) {
        with(itemView) {
            input_subject_tv.visibility = View.VISIBLE
            input_subject_tv.text = getText(getItemData(position))
            plus_iv.visibility = View.GONE
        }
    }

    fun visibleDelete(itemView: View, position: Int) {
        with(itemView) {
            plus_iv.visibility = View.VISIBLE
            plus_iv.setImageResource(R.drawable.ic_delete_black_24dp)
            plus_iv.setOnClickListener {
                deleteData(getItemData(position))
                isLongClick = false
            }
        }
    }

    inner class BaseSettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            visibleDone(itemView, position)

            with(itemView) {

                item_rl.setOnLongClickListener {
                    if (!isLongClick) {
                        isLongClick = true
                        clickPosition = position
                        visibleDelete(itemView, position)
                    }
                    return@setOnLongClickListener true
                }


                item_rl.setOnClickListener {
                    if (position == clickPosition) {
                        isLongClick = false
                        plus_iv.visibility = View.GONE
                    }
                }

            }

        }
    }
}