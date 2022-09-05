package com.example.usha.community.detailpages

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.usha.community.detailpages.viewpagers.DetailFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.URL

class ViewUtils(var context: Context) {

    val bigTextSize = 20f
    val displayWidth = context.resources.displayMetrics.widthPixels

    fun getCenterTextWithBackgroundColor(str: String, backgroundColor: Int): View{
        return TextView(context).apply {
            text = str
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.BLACK)
            setBackgroundColor(backgroundColor)
            textSize = bigTextSize
            setPadding(50)
            gravity = Gravity.CENTER
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    fun getRuleText(str: String, isSub: Boolean): View{
        return TextView(context).apply {
            text = str
            textSize = if(isSub) 10f else bigTextSize
            typeface = if(isSub) Typeface.DEFAULT else Typeface.DEFAULT_BOLD
            setTextColor(if(isSub) Color.GRAY else Color.BLACK)
            layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .apply {
                    setMargins(100,0,0, if(isSub) 100 else 30)
                }
        }
    }

    fun getImageViewWithUrl(url: String): View{
        return ImageView(context).also {
            it.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setImageViewUrl(it, url)
        }
    }

    fun setImageViewUrl(imageView: ImageView, url: String) {
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
            .subscribe (
                {imageView.setImageBitmap(it)},
                {Log.e("setImageErr", it.message.toString())}
            )
    }

    fun getBlankView(height: Int): View {
        return View(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
        }
    }

    fun getShallowLineView(): View{
        return View(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2)
            setBackgroundColor(Color.BLUE)
        }
    }

}