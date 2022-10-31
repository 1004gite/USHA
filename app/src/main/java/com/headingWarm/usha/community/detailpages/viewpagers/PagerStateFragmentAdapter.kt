package com.headingWarm.usha.community.detailpages.viewpagers

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerStateFragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    var fragments : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
//        Log.e("adapterCreateFrag",(fragments[position] as DetailFragment).getViewListSize().toString())
        return fragments[position]
    }

    fun addFragment(fragment: Fragment){
        fragments.add(fragment)
//        notifyItemInserted(fragments.size - 1)
    }

    fun removeLastFragment(){
        fragments.removeLast()
//        notifyItemRemoved(fragments.size)
    }

//    fun clearFragments(){
//        fragments.clear()
//        notifyDataSetChanged()
//    }
}