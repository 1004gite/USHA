package com.headingWarm.usha.notification

import androidx.lifecycle.ViewModel

class NotificationViewModel : ViewModel() {
    var item = mutableListOf<Int>().apply { add(1) }
}