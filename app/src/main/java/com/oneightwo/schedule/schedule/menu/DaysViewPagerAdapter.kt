package com.oneightwo.schedule.schedule.menu

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.oneightwo.schedule.R
import com.oneightwo.schedule.schedule.base.BaseDayFragment

class DaysViewPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = ArrayList<Fragment>().apply {
        for (i in 0..5) {
            add(BaseDayFragment.newInstance(i))
        }
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount() = fragments.size

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
