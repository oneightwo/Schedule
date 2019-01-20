package com.oneightwo.schedule.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oneightwo.schedule.alarm.AlarmFragment
import com.oneightwo.schedule.R
import com.oneightwo.schedule.schedule.ScheduleFragment
import com.oneightwo.schedule.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

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
            openFragment(ScheduleFragment.newInstance())
        }
        bottom_navigation.setOnNavigationItemSelectedListener(navigationListener)
//        openFragment(ScheduleFragment.newInstance())
    }

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        if (previousMenuItem != item.itemId) {
            previousMenuItem = item.itemId
            when (item.itemId) {
                R.id.action_schedules -> {
                    val dayFragment = ScheduleFragment.newInstance()
                    openFragment(dayFragment)
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
