package com.oneightwo.schedule.tools

import android.app.Application
import com.oneightwo.schedule.database.AppDatabase

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = AppDatabase.getInstance(context = this)
    }
}