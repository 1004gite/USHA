package com.headingWarm.usha.community.detailpages.viewpagers

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.headingWarm.usha.community.item_community.Community
import com.headingWarm.usha.databinding.FragmentMemberBinding

class MemberFragment: Fragment() {

    lateinit var binding: FragmentMemberBinding
    var community: Community? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        community = arguments?.getSerializable("community") as Community?
        binding = FragmentMemberBinding.inflate(inflater).apply {
            lifecycleOwner = this@MemberFragment
            viewModel = ViewModelProvider(this@MemberFragment,
                FragmentMemberViewModel.FragmentMemberVMFac(DetailModel(community!!, resources.displayMetrics.widthPixels, findNavController()).apply { setBitmap(community.mentor_img) }
                )).get(FragmentMemberViewModel::class.java)
        }
        return binding.root
    }

    class FragmentMemberViewModel(val model: DetailModel): ViewModel(){

        val image: LiveData<Bitmap> get() = model.image

        fun clickFloatingBtn(){
            model.clickFloatingBtn()
        }

        class FragmentMemberVMFac(val model: DetailModel): ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FragmentMemberViewModel(model) as T
            }
        }

    }

}