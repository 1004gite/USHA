package com.example.usha.community.detailpages.viewpagers

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.usha.R
import com.example.usha.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var viewList = mutableListOf<View>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        for(view in viewList){
            binding.detailLinearLayout.addView(view)
        }
        return binding!!.root
    }

    fun attachLayout(view: View, index: Int = viewList.size){
        // 마지막은 floatingButton을 위한 공간을 위한 View가 있기 때문에 비원둔다.
        viewList.add(index, view)
    }
}