package com.example.usha.community.detailpages.viewpagers

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usha.MyApplication
import com.example.usha.R
import com.example.usha.community.model_community.Community
import com.example.usha.databinding.FragmentDetailBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment() : Fragment() {
    private lateinit var community: Community
    private lateinit var binding: FragmentDetailBinding
    private var viewList = mutableListOf<View>()
    private lateinit var navController: NavController
//    var recyclerView: RecyclerView? = null
    var fragTag = "baseFrag"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setVariables()
        binding = DataBindingUtil.inflate<FragmentDetailBinding?>(inflater,R.layout.fragment_detail,container,false).apply {
            for(view in viewList){
                detailLinearLayout.addView(view)
            }
//            recyclerView?.let { it.layoutManager = LinearLayoutManager(context) }
            setFloatingBtnListener(floatingBtnJoin)
        }
        return binding!!.root
    }

    fun setVariables(){
        navController = findNavController()
        community = arguments?.getSerializable("community") as Community
    }

    fun setFloatingBtnListener(floatingBtnJoin: ExtendedFloatingActionButton){
        floatingBtnJoin.setOnClickListener {
            if(MyApplication.loginInfo.loginned){
                // 로그인 되어 있으면 가입 페이지로 보낸다.
                val bundle = Bundle().apply {
                    putSerializable("community",community)
                }
                navController.navigate(R.id.reservation, bundle)
            }
            else{
                // 로그인 안되어 있으면 로그인 페이지로 보낸다.
                navController.navigate(R.id.loginMain)
            }
        }
    }

    fun attachLayout(view: View, index: Int = viewList.size){
        // 마지막은 floatingButton을 위한 공간을 위한 View가 있기 때문에 비원둔다.
        viewList.add(index, view)
    }

    override fun onStop() {
        super.onStop()
        viewList.stream().forEach { (it.parent as ViewGroup).removeView(it) }
    }

}