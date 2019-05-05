package com.oneightwo.schedule.tools

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.oneightwo.schedule.R

fun TextView.setTextAndColor(data: String, original: String) {
    text = if (data == original) {
        setTextColor(resources.getColor(R.color.colorGray))
        original
    } else {
        setTextColor(resources.getColor(R.color.colorBlack))
        data
    }
}

fun TextView.checkError(original: String, ic: ImageView) {
    if (text == original) {
        ic.visible()
    } else {
        ic.gone()
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE

}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visibility() {
    if (this.visibility == View.VISIBLE) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }

}

fun log(s: String) {
    Log.e("MyApp", s)
}

fun toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}