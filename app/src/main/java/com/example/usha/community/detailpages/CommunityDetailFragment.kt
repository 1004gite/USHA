package com.example.usha.community.detailpages

import android.os.Bundle
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
import com.example.usha.R
import com.example.usha.community.model.CommunityItemModel
import com.example.usha.databinding.FragmentCommunityDetailBinding

class CommunityDetailFragment() : Fragment() {

    private lateinit var viewModel: CommunityDetailViewModel
    private lateinit var binding: FragmentCommunityDetailBinding
    private lateinit var data: CommunityItemModel
    private lateinit var navController: NavController

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

    }

}