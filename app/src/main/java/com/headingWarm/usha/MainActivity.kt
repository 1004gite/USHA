package com.headingWarm.usha

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.headingWarm.usha.databinding.ActivityMainBinding
import com.headingWarm.usha.dialogUtils.DialogUtils
import com.headingWarm.usha.logins.login.model.LoginApiInterface
import com.headingWarm.usha.logins.login.model.LoginBody
import com.headingWarm.usha.logins.login.model.LoginResult
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        // fragment controller 설정
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        MyApplication.toastPublisher = PublishSubject.create<String?>().apply {
            subscribe { Toast.makeText(applicationContext,it,Toast.LENGTH_SHORT).show() }
        }

        binding.bottomNavView.setupWithNavController(navController)
        checkLoginInfo()
    }

    fun checkLoginInfo(){
        if(!MyApplication.loginInfo.loginned) return
        val loginBody = LoginBody(MyApplication.loginInfo.email,MyApplication.loginInfo.passWord)
        MyApplication.retrofit.create(LoginApiInterface::class.java)
            .getLoginResult(loginBody).enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                if(response.body() == null){
                    // 로그인 실패
                    MyApplication.loginInfo.loginned = false
                }
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                MyApplication.toastPublisher.onNext("네트워크가 불안정하여 다시 로그인 해야합니다")
                MyApplication.loginInfo.loginned = false
            }

        })
    }

    object BindingAdapters {
        @JvmStatic
        @BindingAdapter("imageViewSrcUri")
        fun setImage(imageView: ImageView, uriString: String){
            val stream = CoroutineScope(Dispatchers.IO).async { URL(uriString).openStream() }
            CoroutineScope(Dispatchers.Default).launch {
                val bmp = BitmapFactory.decodeStream(stream.await())
                withContext(Dispatchers.Main){ imageView.setImageBitmap(bmp) }
            }
        }
        @JvmStatic
        @BindingAdapter("bitmapForImageView")
        fun setImage(imageView: ImageView, bitmap: Bitmap?){
            bitmap?.let { imageView.setImageBitmap(it) }
        }

        @JvmStatic
        @BindingAdapter("EFBtnChecked","EFBtnTintList")
        fun checkFloatingBtn(fBtn: ExtendedFloatingActionButton, EFBtnChecked: Boolean, EFBtnTintList: ColorStateList) {
            fBtn.isClickable = EFBtnChecked
            fBtn.backgroundTintList = EFBtnTintList
        }
    }
}