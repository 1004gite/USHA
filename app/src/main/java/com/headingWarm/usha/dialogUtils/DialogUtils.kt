package com.headingWarm.usha.dialogUtils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.headingWarm.usha.databinding.WebviewDialogBinding

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