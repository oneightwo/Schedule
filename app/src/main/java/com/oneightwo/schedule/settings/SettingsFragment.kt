package com.oneightwo.schedule.settings


import android.os.Bundle
import android.view.View
import com.oneightwo.schedule.R
import com.oneightwo.schedule.base.BaseFragment

class SettingsFragment : BaseFragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun getLayoutId() = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val adapter = ItemsViewPagerAdapter(childFragmentManager)
//        val adapter = ItemsViewPagerAdapter(context ?: return, childFragmentManager)
//        setting_vp.adapter = adapter
//        setting_tl.setupWithViewPager(setting_vp)
    }
}
