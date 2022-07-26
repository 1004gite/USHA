package com.example.usha.community.detailpages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.usha.R
import com.example.usha.community.detailpages.viewpagers.DetailFragment
import com.example.usha.community.detailpages.viewpagers.PagerStateFragmentAdapter
import com.example.usha.community.model.CommunityItemModel
import com.example.usha.databinding.FragmentCommunityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class CommunityDetailFragment() : Fragment() {

    private lateinit var viewModel: CommunityDetailViewModel
    private lateinit var binding: FragmentCommunityDetailBinding
    private lateinit var data: CommunityItemModel
    private lateinit var navController: NavController
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_community_detail,container,false)
        data = arguments?.getSerializable("test") as CommunityItemModel
        navController = this.findNavController()
        binding!!.communityDetailBackBtn.setOnClickListener {
            // 뒤로가기 눌렀을 때 지금 페이지까지 오는 경로는 stack에서 제외하는 옵션
            var navOps: NavOptions = NavOptions.Builder().setPopUpTo(R.id.community,true).build()
            navController.navigate(R.id.community,null,navOps)
        }
        setViewPager()

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommunityDetailViewModel::class.java)
    }

    fun setViewPager(){
        viewPager = binding!!.communityPager
        viewPager.adapter = PagerStateFragmentAdapter(this).apply {
            addFragment(DetailFragment())
            addFragment(DetailFragment())
            addFragment(DetailFragment())
            addFragment(DetailFragment())
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })
        // tablayout attach
        val titles = arrayOf("개요","커리큘럼","구성원","이용후기")
        TabLayoutMediator(binding!!.communiotyTabLayout, viewPager){ tab, position ->
            tab.text = titles[position]
//            Log.e("tabLog", position.toString())
        }.attach()
    }

}