package com.oneightwo.schedule.settings

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.oneightwo.schedule.settings.fragment.CabinetFragment
import com.oneightwo.schedule.settings.fragment.SubjectFragment
import com.oneightwo.schedule.settings.fragment.TeacherFragment
import com.oneightwo.schedule.settings.fragment.TimeFragment


class ItemsViewPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayListOf<Fragment>(
        SubjectFragment.newInstance(),
        TimeFragment.newInstance(),
        CabinetFragment.newInstance(),
        TeacherFragment.newInstance()
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): String = when (position) {
        0 -> "Предметы"
        1 -> "Время пар"
        2 -> "Кабинеты"
        3 -> "Преподаватели"
        else -> ""
    }
}