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
                    FragmentCurriculumModel(resources.displayMetrics.widthPixels, community!!, findNavController())
                )
            ).get(FragmentCurriculumViewModel::class.java)
        }
        return binding.root
    }


    class FragmentCurriculumViewModel(val model: FragmentCurriculumModel): ViewModel(){

        val image: LiveData<Bitmap> get() = model.image

        fun clickFloatingBtn(){
            model.clickFloatingBtn()
        }

        class FragmentCurriculumVMFac(val model: FragmentCurriculumModel): ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FragmentCurriculumViewModel(model) as T
            }
        }

    }


    class FragmentCurriculumModel(val displayWidth: Int, val community: Community, val navController: NavController){
        var image = MutableLiveData<Bitmap>()

        init {
            setBitmap(image,community.curriculum_img)
        }

        fun setBitmap(imgData:MutableLiveData<Bitmap>, url: String){
            Observable.just(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map {
                    var iStream = URL(url).openStream()
                    var bmp = BitmapFactory.decodeStream(iStream)
                    var height = (displayWidth.toFloat()/bmp.width.toFloat())*bmp.height
                    Bitmap.createScaledBitmap(bmp,displayWidth, height.toInt(),false)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {imgData.value = it}
        }

        fun clickFloatingBtn(){
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
}