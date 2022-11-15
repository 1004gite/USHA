package com.headingWarm.usha.community.detailpages.viewpagers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.R
import com.headingWarm.usha.community.item_community.Community
import kotlinx.coroutines.*
import java.net.URL

class DetailModel(val community: Community, val displayWidth: Int, val navController: NavController){
    val image = MutableLiveData<Bitmap>()

    fun setBitmap(url: String) {
        val stream = CoroutineScope(Dispatchers.IO).async { URL(url).openStream() }
        CoroutineScope(Dispatchers.Default).launch {
            var bmp = BitmapFactory.decodeStream(stream.await())
            val height = (displayWidth.toFloat() / bmp.width.toFloat()) * bmp.height
            bmp = Bitmap.createScaledBitmap(bmp, displayWidth, height.toInt(), false)

            withContext(Dispatchers.Main) { image.value = bmp }
        }
    }

    fun clickFloatingBtn() {
        if (MyApplication.loginInfo.loginned) {
            // 로그인 되어 있으면 가입 페이지로 보낸다.
            val bundle = Bundle().apply {
                putSerializable("community", community)
            }
            navController.navigate(R.id.reservation, bundle)
        } else {
            // 로그인 안되어 있으면 로그인 페이지로 보낸다.
            navController.navigate(R.id.loginMain)
        }
    }

}