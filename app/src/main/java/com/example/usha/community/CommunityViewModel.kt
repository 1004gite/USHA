package com.example.usha.community

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityViewModel : ViewModel() {
    private var _text = MutableLiveData<String>("communityFragment")

    val text: MutableLiveData<String> get() = _text
}