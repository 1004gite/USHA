package com.example.usha.community.detailpages

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.usha.R
import com.example.usha.community.detailpages.viewpagers.DetailFragment
import com.example.usha.community.detailpages.viewpagers.PagerStateFragmentAdapter
import com.example.usha.community.model.Community
import com.example.usha.databinding.FragmentCommunityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.URL

class CommunityDetailFragment() : Fragment() {

//    private lateinit var viewModel: CommunityDetailViewModel
    private lateinit var binding: FragmentCommunityDetailBinding
    private lateinit var data: Community
    private lateinit var navController: NavController
    private lateinit var viewPager: ViewPager2
    private lateinit var mContext: Context
    private lateinit var viewUtils: ViewUtils
    private var blackHeight = 500

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_community_detail,container,false)
        data = arguments?.getSerializable("info") as Community
        mContext = requireContext()
        viewUtils = ViewUtils(mContext)
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
//        viewModel = ViewModelProvider(this).get(CommunityDetailViewModel::class.java)
    }

    fun setViewPager(){
        viewPager = binding!!.communityPager
        viewPager.adapter = PagerStateFragmentAdapter(this).apply {
            addFragment(getSummaryFrag())
            addFragment(getCurriculumFrag())
            addFragment(getMemberFrag())
            addFragment(getReviewFrag())
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

    fun getSummaryFrag(): Fragment{
        var imageView = ImageView(mContext)
        viewUtils.setImageViewUrl(imageView, data.introduce_img)
        var frag = DetailFragment().apply {
            attachLayout(imageView)
            attachLayout(viewUtils.getRuleText(data.rule1,false))
            attachLayout(viewUtils.getRuleText(data.rule1_sub,true))
            attachLayout(viewUtils.getRuleText(data.rule2,false))
            attachLayout(viewUtils.getRuleText(data.rule2_sub,true))
            attachLayout(viewUtils.getCenterTextWithBackgroundColor(
                "${data.goalTerm}, 여러분은\n${data.goalName}을 이룰 수 있습니다!",
                Color.argb(70,255,100,255)
            ))

            attachLayout(viewUtils.getBlankView(blackHeight))
        }

        return frag
    }

    fun getCurriculumFrag(): Fragment{
        var frag = DetailFragment().apply {


            attachLayout(viewUtils.getBlankView(blackHeight))
        }

        return frag
    }

    fun getMemberFrag(): Fragment{
        var frag = DetailFragment().apply {


            attachLayout(viewUtils.getBlankView(blackHeight))
        }

        return frag
    }

    fun getReviewFrag(): Fragment{
        var frag = DetailFragment().apply {


            attachLayout(viewUtils.getBlankView(blackHeight))
        }

        return frag
    }

}