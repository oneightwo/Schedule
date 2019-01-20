package com.oneightwo.schedule.schedule

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.oneightwo.schedule.R

class ViewPagerAdapter(private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return DayFragment()
    }

    override fun getCount() = 6

    override fun getPageTitle(position: Int): String = when(position) {
            0 -> context.getString(R.string.monday)
            1 -> context.getString(R.string.tuesday)
            2 -> context.getString(R.string.wednesday)
            3 -> context.getString(R.string.thursday)
            4 -> context.getString(R.string.friday)
            5 -> context.getString(R.string.saturday)
            else -> ""
        }

}
