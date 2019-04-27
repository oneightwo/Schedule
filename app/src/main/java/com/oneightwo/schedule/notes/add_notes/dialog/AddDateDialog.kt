package com.oneightwo.schedule.notes.add_notes.dialog

import android.app.Activity
import android.app.DatePickerDialog

class AddDateDialog(
    private val context: Activity,
    private val setDate: (String) -> Unit
) : DatePickerDialog(context) {

    override fun dismiss() {
        with(datePicker) {
            setDate("$dayOfMonth-$month-$year")
        }
        super.dismiss()
    }
}