package com.example.usha.dialogUtils

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.example.usha.MyApplication
import com.example.usha.databinding.WebviewDialogBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class DialogUtils {

    fun getWebViewDialog(context: Context, xmlId: Int, url: String): Dialog {
        return Dialog(context).also { dial ->
            dial.setContentView(
                DataBindingUtil.inflate<WebviewDialogBinding>(LayoutInflater.from(context), xmlId, null, false).apply {
                    btnClose.setOnClickListener { dial.dismiss() }
                    dialogWebView.webViewClient = WebViewClient()
                    dialogWebView.settings.javaScriptEnabled = true
                    dialogWebView.post { dialogWebView.loadUrl(url) }
                }.root
            )
            dial.window?.attributes = dial.window?.attributes?.apply {
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
                verticalMargin = 10f
                horizontalMargin = 10f
            }
        }
    }

}