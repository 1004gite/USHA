package com.headingWarm.usha.community.detailpages

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.headingWarm.usha.R
import com.headingWarm.usha.community.detailpages.viewpagers.*
import com.headingWarm.usha.community.item_community.Community
import com.headingWarm.usha.databinding.FragmentCommunityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class CommunityDetailFragment() : Fragment() {

    private lateinit var binding: FragmentCommunityDetailBinding
    private lateinit var community: Community
    private lateinit var navController: NavController
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        community = arguments?.getSerializable("community") as Community
        navController = findNavController()
        binding = FragmentCommunityDetailBinding.inflate(inflater).apply {
            lifecycleOwner = this@CommunityDetailFragment.viewLifecycleOwner
            communityDetailBackBtn.setOnClickListener {
                // 뒤로가기 눌렀을 때 지금 페이지까지 오는 경로는 stack에서 제외하는 옵션
                var navOps: NavOptions = NavOptions.Builder().setPopUpTo(R.id.community,true).build()
                navController.navigate(R.id.community,null,navOps)
            }
            nameTextView.text = community.name
        }
        setViewPager()
        return binding.root
    }

    fun setViewPager(){
        viewPager = binding.communityPager
        viewPager.adapter = PagerStateFragmentAdapter(this@CommunityDetailFragment)
        with(viewPager.adapter as PagerStateFragmentAdapter) {
                addFragment(SummaryFragment().apply { arguments = this@CommunityDetailFragment.arguments })
                addFragment(CurriculumFragment().apply { arguments = this@CommunityDetailFragment.arguments })
                addFragment(MemberFragment().apply { arguments = this@CommunityDetailFragment.arguments })
                addFragment(ReviewFragment().apply { arguments = this@CommunityDetailFragment.arguments})
        }

        // tablayout attach
        val titles = arrayOf("개요","커리큘럼","구성원","이용후기")
        TabLayoutMediator(binding.communiotyTabLayout, viewPager){ tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    class PagerStateFragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
        var fragments : ArrayList<Fragment> = ArrayList()

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment: Fragment){
            fragments.add(fragment)
        }

        fun removeLastFragment(){
            fragments.removeLast()
        }
    }
}