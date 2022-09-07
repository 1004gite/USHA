package com.example.usha.community.detailpages

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.usha.R
import com.example.usha.community.detailpages.viewpagers.*
import com.example.usha.community.model_community.Community
import com.example.usha.databinding.FragmentCommunityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class CommunityDetailFragment() : Fragment() {

    private lateinit var binding: FragmentCommunityDetailBinding
    private lateinit var community: Community
    private lateinit var navController: NavController
    private lateinit var viewPager: ViewPager2
    private lateinit var mContext: Context
    private lateinit var viewUtils: ViewUtils
    private var blankHeight = 500
    private val imageScaleType = ImageView.ScaleType.FIT_CENTER

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        community = arguments?.getSerializable("community") as Community
        mContext = requireContext()
        viewUtils = ViewUtils(mContext)
        navController = this.findNavController()
        binding = DataBindingUtil.inflate<FragmentCommunityDetailBinding?>(inflater,R.layout.fragment_community_detail,container,false).apply {
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


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setViewPager()
//    }

    fun setViewPager(){
        viewPager = binding.communityPager.apply {
            adapter = PagerStateFragmentAdapter(this@CommunityDetailFragment).apply {
                addFragment(getSummaryFrag())
                addFragment(getCurriculumFrag())
                addFragment(getMemberFrag())
                addFragment(getReviewFrag())
            }

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int){
                    super.onPageSelected(position)
//                    Log.e("ViewPagerFragment", "Page ${position+1}")
                }
            })
        }

        // tablayout attach
        val titles = arrayOf("개요","커리큘럼","구성원","이용후기")
        TabLayoutMediator(binding!!.communiotyTabLayout, viewPager){ tab, position ->
            tab.text = titles[position]
//            Log.e("tabLog", position.toString())
        }.attach()

//        Log.e("reviewLog", community.reviews[0].toString())
    }

    fun getSummaryFrag(): Fragment{
        var frag = DetailFragment().apply {
            fragTag = "SummaryTag"
            arguments = this@CommunityDetailFragment.arguments
            attachLayout(viewUtils.getImageViewWithUrl(community.introduce_img))
//          이미지 스케일 지정하는 코드      .apply { (this as ImageView).scaleType = imageScaleType })
            attachLayout(viewUtils.getRuleText(this@CommunityDetailFragment.community.rule1,false))
            attachLayout(viewUtils.getRuleText(this@CommunityDetailFragment.community.rule1_sub,true))
            attachLayout(viewUtils.getRuleText(this@CommunityDetailFragment.community.rule2,false))
            attachLayout(viewUtils.getRuleText(this@CommunityDetailFragment.community.rule2_sub,true))
            attachLayout(viewUtils.getCenterTextWithBackgroundColor(
                "${this@CommunityDetailFragment.community.goalTerm}, 여러분은\n${this@CommunityDetailFragment.community.goalName}을 이룰 수 있습니다!",
                Color.argb(70,255,100,255)
            ))

            attachLayout(viewUtils.getBlankView(blankHeight))
        }

        return frag
    }

    fun getCurriculumFrag(): Fragment{
        var frag = DetailFragment().apply {
            fragTag = "CurriculFrag"
            arguments = this@CommunityDetailFragment.arguments
            attachLayout(viewUtils.getImageViewWithUrl(community.curriculum_img))

            attachLayout(viewUtils.getBlankView(blankHeight))
        }

        return frag
    }

    fun getMemberFrag(): Fragment{
        var recyclerView = RecyclerView(mContext).apply {
            adapter = MemberRecyclerAdapter(listOf("test"))
            layoutManager = LinearLayoutManager(mContext)
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }
        var frag = DetailFragment().apply {
            fragTag = "MemberFrag"
            arguments = this@CommunityDetailFragment.arguments
            attachLayout(viewUtils.getImageViewWithUrl(community.mentor_img))
            attachLayout(recyclerView)

            attachLayout(viewUtils.getBlankView(blankHeight))
        }

        return frag
    }

    fun getReviewFrag(): Fragment{
        var recyclerView = RecyclerView(mContext).apply {
            adapter = ReviewRecyclerAdapter(community.reviews)
            layoutManager = LinearLayoutManager(mContext)
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
                )
        }
        var frag = DetailFragment().apply {
            fragTag = "ReviewFrag"
            arguments = this@CommunityDetailFragment.arguments
            attachLayout(recyclerView)

            attachLayout(viewUtils.getBlankView(blankHeight))
        }

        return frag
    }

    override fun onStop() {
        super.onStop()
        binding.communityPager.adapter = null
    }
}