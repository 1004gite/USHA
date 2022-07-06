package com.example.usha.community

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usha.R
import com.example.usha.community.recyclerview.CommunityRecyclerAdapter
import com.example.usha.databinding.FragmentCommunityBinding

class CommunityFragment() : Fragment() {

    private var binding: FragmentCommunityBinding? = null
    private lateinit var viewModel: CommunityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_community,container,false)
        viewModel = ViewModelProvider(this).get(CommunityViewModel::class.java)
        binding!!.viewModel = viewModel

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val deviceHeight = resources.displayMetrics.heightPixels
        val navController = this.findNavController()
        val adapter = CommunityRecyclerAdapter(deviceHeight/5*2, navController)
        binding!!.communityRecyclerView.layoutManager = LinearLayoutManager(context) // 스크롤 관련 매니저 지정
        binding!!.communityRecyclerView.adapter = adapter
    }

}