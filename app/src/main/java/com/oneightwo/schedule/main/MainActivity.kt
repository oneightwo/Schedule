package com.oneightwo.schedule.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oneightwo.schedule.R
import com.oneightwo.schedule.alarm.AlarmFragment
import com.oneightwo.schedule.notes.NotesFragment
import com.oneightwo.schedule.schedule.main.ScheduleFragment
import com.oneightwo.schedule.settings.SettingsFragment
import com.oneightwo.schedule.tools.log
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var previousMenuItem = R.id.action_schedules

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState?.putInt("opened_fragment", bottom_navigation.selectedItemId)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            bottom_navigation.selectedItemId = savedInstanceState.getInt("opened_fragment")
        } else {
            openFragment(ScheduleFragment.newInstance(getWeek(), getDay()))
        }
        bottom_navigation.setOnNavigationItemSelectedListener(navigationListener)
    }

    private fun getDay(): Int {
        val calendar = Calendar.getInstance()
        val date = calendar.time
        log("DATE -> ${SimpleDateFormat("u", Locale.ENGLISH).format(date.time)}")
        return if (SimpleDateFormat("u", Locale.ENGLISH).format(date.time).toInt() == 7)
            1
        else SimpleDateFormat("u", Locale.ENGLISH).format(date.time).toInt()
    }

    private fun getWeek(): Int {
        val calendar = Calendar.getInstance()
        val date = calendar.time
        log("WEEK -> ${SimpleDateFormat("w", Locale.ENGLISH).format(date.time)}")
        return if (SimpleDateFormat("w", Locale.ENGLISH).format(date.time).toInt() % 2 == 0) {
            log("WEEK_NUMBER -> 2")
            2
        } else {
            log("WEEK_NUMBER -> 1")
            1
        }
    }

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        if (previousMenuItem != item.itemId) {
            previousMenuItem = item.itemId
            when (item.itemId) {
                R.id.action_schedules -> {
                    val dayFragment = ScheduleFragment.newInstance(getWeek(), getDay())
                    openFragment(dayFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_notes -> {
                    val notesFragment = NotesFragment.newInstance()
                    openFragment(notesFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_alarms -> {
                    val alarmFragment = AlarmFragment.newInstance()
                    openFragment(alarmFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_settings -> {
                    val settingsFragment = SettingsFragment.newInstance()
                    openFragment(settingsFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
        }
        false
    }

    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
