package com.oneightwo.schedule.notes.add_notes.dialog

import android.app.Activity
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import java.util.*

class AddTimeDialog(
    private val context: Activity,
    private val setData: (String) -> Unit
) : TimePickerDialog(context, OnTimeSetListener { _, hourOfDay, minute ->
    if (minute < 10) setData("$hourOfDay:0$minute")
    else setData("$hourOfDay:$minute")
}, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true) {


}
