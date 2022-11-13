package com.headingWarm.usha.community.detailpages.viewpagers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.R
import com.headingWarm.usha.community.item_community.Community
import com.headingWarm.usha.databinding.FragmentCurriculumBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.URL

class CurriculumFragment: Fragment() {

    lateinit var binding: FragmentCurriculumBinding
    var community: Community? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        community = arguments?.getSerializable("community") as Community?
        binding = FragmentCurriculumBinding.inflate(inflater).apply {
            lifecycleOwner = this@CurriculumFragment
            viewModel = ViewModelProvider(
                this@CurriculumFragment,
                FragmentCurriculumViewModel.FragmentCurriculumVMFac(
                    DetailModel(community!!, resources.displayMetrics.widthPixels, findNavController())
                        .apply { setBitmap(community.curriculum_img) }
                )
            ).get(FragmentCurriculumViewModel::class.java)
        }
        return binding.root
    }


    class FragmentCurriculumViewModel(val model: DetailModel): ViewModel(){

        val image: LiveData<Bitmap> get() = model.image

        fun clickFloatingBtn(){
            model.clickFloatingBtn()
        }

        class FragmentCurriculumVMFac(val model: DetailModel): ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FragmentCurriculumViewModel(model) as T
            }
        }

    }
}