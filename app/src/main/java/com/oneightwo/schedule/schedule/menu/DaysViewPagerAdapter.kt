package com.oneightwo.schedule.schedule.menu

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.oneightwo.schedule.R
import com.oneightwo.schedule.schedule.day.DayFragment

class DaysViewPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return DayFragment.newInstance(position)
    }

    override fun getCount() = 6

    override fun getPageTitle(position: Int): String = when (position) {
        0 -> context.getString(R.string.monday)
        1 -> context.getString(R.string.tuesday)
        2 -> context.getString(R.string.wednesday)
        3 -> context.getString(R.string.thursday)
        4 -> context.getString(R.string.friday)
        5 -> context.getString(R.string.saturday)
        else -> ""
    }

}
