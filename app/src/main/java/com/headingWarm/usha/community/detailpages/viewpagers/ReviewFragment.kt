package com.headingWarm.usha.community.detailpages.viewpagers

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.headingWarm.usha.community.item_community.Community
import com.headingWarm.usha.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {

    lateinit var binding: FragmentReviewBinding
    var community: Community? = null
//    lateinit var recyclerViewAdapter: ReviewRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        community = arguments?.getSerializable("community") as Community?
//        recyclerViewAdapter = ReviewRecyclerAdapter(community!!.reviews, community!!._id)
        binding = FragmentReviewBinding.inflate(inflater).apply {
            lifecycleOwner = this@ReviewFragment
            viewModel = ViewModelProvider(this@ReviewFragment,
                    FragmentReviewViewModel.FragmentReviewVMfac(DetailModel(community!!, resources.displayMetrics.widthPixels, findNavController()))
                ).get(FragmentReviewViewModel::class.java)
            reviewRecyclerView.adapter = ReviewRecyclerAdapter(community!!.reviews, community!!._id)
        }

        return binding.root
    }

    class FragmentReviewViewModel(val model: DetailModel) : ViewModel() {
        val spannableStr1 = with("Data로 확인하는 수강효과") {
            SpannableStringBuilder(this).apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(RelativeSizeSpan(1.3f), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(StyleSpan(Typeface.BOLD), 10, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(RelativeSizeSpan(1.3f), 10, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(
                    BackgroundColorSpan(Color.parseColor("#E2C9FF")),
                    0, this.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        val spannableStr2 = with("Review로 확인하는 수강효과"){
            SpannableStringBuilder(this).apply {
                setSpan(StyleSpan(android.graphics.Typeface.BOLD), 0, 6, android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(RelativeSizeSpan(1.3f),0,6, android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(StyleSpan(android.graphics.Typeface.BOLD), 13, 17, android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(RelativeSizeSpan(1.3f),13,17, android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(BackgroundColorSpan(android.graphics.Color.parseColor("#E2C9FF")),
                    0, this.length, android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        fun clickFloatingBtn(){
            model.clickFloatingBtn()
        }

        class FragmentReviewVMfac(val model: DetailModel): ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FragmentReviewViewModel(model) as T
            }
        }

    }
}