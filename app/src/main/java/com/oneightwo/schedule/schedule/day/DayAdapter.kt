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
import com.oneightwo.schedule.schedule.base.FormatOutput
import kotlinx.android.synthetic.main.item_day.view.*

class DayAdapter : BaseAdapter<FormatOutput, DayAdapter.DataViewHolder>() {

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
                teacher_tv.text = getItemData(position).teacher
                if (getItemData(position).type != null) {
                    type_iv.visibility = View.VISIBLE
                    type_iv.setImageResource(getItemData(position).type!!)
                }
                else {
                    type_iv.visibility = View.GONE
                }
//                type_iv.setImageResource(getItemData(position).type)
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