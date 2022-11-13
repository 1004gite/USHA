package com.headingWarm.usha.community.detailpages.viewpagers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.R
import com.headingWarm.usha.community.item_community.Community
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.URL

open class DetailModel(val community: Community, val displayWidth: Int, val navController: NavController){
    val image = MutableLiveData<Bitmap>()

    fun setBitmap(url: String){
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
            .subscribe {image.value = it}
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