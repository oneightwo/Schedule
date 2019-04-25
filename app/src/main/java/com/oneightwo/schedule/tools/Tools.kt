package com.oneightwo.schedule.tools

import android.content.Context
import android.util.Log
import android.widget.Toast

fun log(s: String){
    Log.e("MyApp", s)
}

fun toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}