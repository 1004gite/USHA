package com.example.usha.community.detailpages.viewpagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

// 이 어댑터를 가지는 fragment를 넣는 것이 맞나?
class ViewPagerAdapter(frag: Fragment): FragmentStateAdapter(frag) {
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}