package com.oneightwo.schedule.schedule.day

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import com.oneightwo.schedule.database.schedule.Schedule
import kotlinx.android.synthetic.main.item_day.view.*

class DayAdapter : BaseAdapter<Schedule, DayAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                subject_tv.text = getItemData(position).subject
                time_tv.text = getItemData(position).time
                cabinet_tv.text = getItemData(position).cabinet
            }

        }

        private fun animate(ivRotated: ImageView, ivShown: ImageView) {
            val animation = AnimationUtils.loadAnimation(ivRotated.context, R.anim.rotate_arrow)
            animation.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    ivRotated.visibility = View.GONE
                    ivShown.visibility = View.VISIBLE
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            })
            ivRotated.startAnimation(animation)
        }
    }
}