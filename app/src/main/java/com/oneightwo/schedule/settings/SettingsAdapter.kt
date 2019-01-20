package com.oneightwo.schedule.settings

import android.text.InputType.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import kotlinx.android.synthetic.main.item_settings.view.*

class SettingsAdapter(private val addData: (String) -> Unit) :
    BaseAdapter<String, SettingsAdapter.SettingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_settings, parent, false)
        return SettingsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    var indexMenu: Int = 0

    private fun formatInput(itemView: View, index: Int) {
        with(itemView.input_et) {
            when (index) {
                0 -> {
                    hint = context.getString(R.string.example_subject)
                    inputType = TYPE_CLASS_TEXT + TYPE_TEXT_FLAG_CAP_SENTENCES
                }
                1 -> {
                    hint = context.getString(R.string.example_time)
                    inputType = TYPE_CLASS_DATETIME
                }
                2 -> {
                    hint = context.getString(R.string.example_cabinet)
                    inputType = TYPE_CLASS_NUMBER
                }
                3 -> {
                    hint = context.getString(R.string.example_teacher)
                    inputType = TYPE_TEXT_VARIATION_PERSON_NAME + TYPE_TEXT_FLAG_CAP_WORDS
                }
            }
        }
    }

    private fun onClick(itemView: View) {
        with(itemView) {
            add_subject_rl.setOnClickListener {
                if (!input_et.text.isNullOrEmpty()) {
                    addData(input_et.text.toString())
                    input_et.setText("")
                }
            }
        }
    }

    private fun visibleAdd(itemView: View) {
        with(itemView) {
            input_subject_tv.visibility = View.GONE
            input_et.visibility = View.VISIBLE
            plus_iv.setImageResource(R.drawable.ic_add_black_24dp)
        }
    }

    private fun visibleDone(itemView: View, position: Int) {
        with(itemView) {
            input_subject_tv.visibility = View.VISIBLE
            input_subject_tv.text = getItemData(position)
            plus_iv.setImageResource(R.drawable.ic_done_black_24dp)
            input_et.visibility = View.GONE
        }
    }

    inner class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            formatInput(itemView, indexMenu)
            if (position != size()) {
                visibleDone(itemView, position)
            } else {
                visibleAdd(itemView)
                onClick(itemView)
            }
        }
    }
}