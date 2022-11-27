package com.headingWarm.usha.community.detailpages.viewpagers

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.headingWarm.usha.community.item_community.Community
import com.headingWarm.usha.databinding.FragmentSummaryBinding

class SummaryFragment: Fragment() {

    lateinit var binding: FragmentSummaryBinding
    var community: Community? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        community = arguments?.getSerializable("community") as Community
        binding = FragmentSummaryBinding.inflate(inflater).apply {
            lifecycleOwner = this@SummaryFragment.viewLifecycleOwner
            viewModel = ViewModelProvider(this@SummaryFragment,
                FragmentSummaryViewModel.FragmentSummaryVMFac(
                    DetailModel(this@SummaryFragment.community!!,resources.displayMetrics.widthPixels,  findNavController()).apply { setBitmap(community.introduce_img) })
            ).get(FragmentSummaryViewModel::class.java)
        }
        return binding.root
    }

    class FragmentSummaryViewModel(val model: DetailModel): ViewModel(){
        val community = model.community
        val image: LiveData<Bitmap> get() = model.image
        val textCenter = "${community.goalTerm}, 여러분은\n${community.goalName}을 이룰 수 있습니다!"

        fun clickFloatingBtn(){
            model.clickFloatingBtn()
        }

        class FragmentSummaryVMFac(val model: DetailModel): ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FragmentSummaryViewModel(model) as T
            }
        }
    }
}