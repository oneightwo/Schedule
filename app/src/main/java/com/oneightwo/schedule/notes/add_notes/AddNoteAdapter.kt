package com.oneightwo.schedule.notes.add_notes

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseAdapter
import com.oneightwo.schedule.notes.add_notes.AddNoteActivity.Companion.NOTIFICATION_POSITION
import com.oneightwo.schedule.notes.add_notes.AddNoteActivity.Companion.SUBJECT_POSITION
import com.oneightwo.schedule.notes.add_notes.AddNoteActivity.Companion.TIME_POSITION
import com.oneightwo.schedule.tools.IC_ITEM_ADD_NOTE_MENU
import com.oneightwo.schedule.tools.ITEM_ADD_NOTE_MENU
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.item_add_note.view.*

class AddNoteAdapter(
    private val setData: (Int, Any) -> Unit,
//    private val getText: () -> String
    private val openDialog: (Int) -> Unit
) : BaseAdapter<Any, AddNoteAdapter.AddNoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddNoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_add_note, parent, false)
        return AddNoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddNoteViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AddNoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                icon_iv.setImageResource(IC_ITEM_ADD_NOTE_MENU[position])
                when (position) {
                    SUBJECT_POSITION, TIME_POSITION -> {
                        item_menu_note_et.isClickable = false
                        item_menu_note_et.isFocusable = false
                        if ((getItemData(position) as String) == ITEM_ADD_NOTE_MENU[position]) {
//                            item_menu_note_et.append(ITEM_ADD_NOTE_MENU[position])
                            item_menu_note_et.setText(ITEM_ADD_NOTE_MENU[position])
                        } else {
                            item_menu_note_et.setText(getItemData(position) as String)
                        }
                        item_menu_note_et.setOnClickListener {
                            openDialog(position)
                        }

                    }
                    NOTIFICATION_POSITION -> {
                        item_menu_note_et.isClickable = false
                        item_menu_note_et.isFocusable = false
                        item_menu_note_et.setText(ITEM_ADD_NOTE_MENU[position])
                        enable_notification_s.visibility = View.VISIBLE
                        enable_notification_s.isChecked = getItemData(position) as Boolean
                        enable_notification_s.setOnCheckedChangeListener { _, isChecked ->
                            setData(position, isChecked)
                        }
                    }
                    else -> {
                        item_menu_note_et.hint = getItemData(position) as String
                        item_menu_note_et?.addTextChangedListener(object : TextWatcher {
                            override fun afterTextChanged(s: Editable?) {}

                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                                log("onTextChanged $s $start $before $count ")
                                setData(position, s.toString())
                            }
                        })

                    }
                }
            }
        }
    }
}