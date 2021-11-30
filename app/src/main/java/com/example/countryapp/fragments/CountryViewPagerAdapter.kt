package com.example.countryapp.fragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class CountryViewPagerAdapter(fragment: Fragment, val mFragmentList: Array<String>, val viewPager: ViewPager2) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}