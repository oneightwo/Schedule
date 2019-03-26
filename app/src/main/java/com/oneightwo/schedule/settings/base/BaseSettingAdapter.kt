package com.oneightwo.schedule.settings.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_settings.view.*

abstract class BaseSettingAdapter<T>(
    private val addDataDeletions: (Int, T) -> Unit,
    private val removeDataDeletions: (Int, T) -> Unit,
    private val listDataDeletions: () -> ArrayList<FormDataSetting<T>>,
    private val changeStateFAB: (Boolean) -> Unit,
    private val stateFAB: () -> Boolean
) : BaseAdapter<T, BaseSettingAdapter<T>.BaseSettingsViewHolder>() {

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
            if (!stateFAB()) {
                check_box_iv.visibility = View.VISIBLE
                if (listDataDeletions().map { it.position }.contains(position))
                    check_box_iv.setImageResource(R.drawable.ic_check_box_black_24dp)
                else
                    check_box_iv.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp)
            } else {
                check_box_iv.visibility = View.GONE
            }
        }
    }

    inner class BaseSettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                init(itemView, position)
                input_subject_tv.text = getText(getItemData(position))
                item_rl.setOnLongClickListener {
                    if (stateFAB()) {
                        changeStateFAB(false)
                    }
                    return@setOnLongClickListener true
                }

                item_rl.setOnClickListener {
                    if (!stateFAB()) {
                        if (listDataDeletions().map { it.position }.contains(position)) {
                            removeDataDeletions(position, getItemData(position))
                        } else {
                            addDataDeletions(position, getItemData(position))
                            init(itemView, position)
                        }
                        if (listDataDeletions().isEmpty()) {
                            changeStateFAB(true)
                        }
                    }
                }
            }
        }
    }
}