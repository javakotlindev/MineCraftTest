package com.tellit.minecrafttest.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: Fragment, var orderFragmentList: List<Fragment>) :
    FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = orderFragmentList.size
    override fun createFragment(position: Int): Fragment = orderFragmentList[position]
}