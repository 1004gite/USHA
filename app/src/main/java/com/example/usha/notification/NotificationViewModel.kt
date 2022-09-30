package com.example.usha.notification

import androidx.lifecycle.ViewModel
import com.example.usha.MyApplication

class NotificationViewModel : ViewModel() {
    var item = mutableListOf<Int>().apply { add(1) }
}