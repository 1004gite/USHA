package com.example.usha

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.usha.community.CommunityFragment
import com.example.usha.databinding.ActivityMainBinding
import com.example.usha.notification.NotificationFragment
import com.example.usha.profile.ProfileFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding!!.lifecycleOwner = this

        // fragment controller 설정
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding!!.bottomNavView.setupWithNavController(navController)
    }

    object BindingAdapters {
        @JvmStatic
        @BindingAdapter("imageViewSrcUri")
        fun setImage(imageView: ImageView, uriString: String){
            // subscribeOn -> 스트림 액션이 끝난 후 다음 스트림으로 옮길 스케쥴러를 설정한다.
            // observeOn -> 설정 이후 스트림의 액션을 수행할 스케쥴러를 지정한다.
            Observable.just(uriString)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map {
                    var iStream = URL(uriString).openStream()
                    BitmapFactory.decodeStream(iStream)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    imageView.setImageBitmap(it)
                }
        }

        @JvmStatic
        @BindingAdapter("EFBtnChecked","EFBtnTintList")
        fun checkFloatingBtn(fBtn: ExtendedFloatingActionButton, EFBtnChecked: Boolean, EFBtnTintList: ColorStateList) {
            fBtn.isClickable = EFBtnChecked
            fBtn.backgroundTintList = EFBtnTintList
        }
    }
}