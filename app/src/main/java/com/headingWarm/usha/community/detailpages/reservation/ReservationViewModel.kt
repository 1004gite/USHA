package com.headingWarm.usha.community.detailpages.reservation

import android.app.Dialog
import android.content.res.ColorStateList
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.community.item_community.Community
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationViewModel(val model: ReservationModel) : ViewModel() {
    val community = model.community
    val checkedTerms: MutableLiveData<Boolean> get() = model.checkedTerms
    val tintList: LiveData<ColorStateList> get() = model.tintList
    val userName: MutableLiveData<String> get() = model.userName
    val userEmail: MutableLiveData<String> get() = model.userEmail
    val userPhoneNumber: MutableLiveData<String> get() = model.userPhoneNumber

    fun clickJoinCommunity(){
        model.requestJoinCommunity()
    }

    fun clickTermBtn(){
        model.showTermWebView()
    }


    // viewModel에서 인자를 받기 위한 custom Factory
    class ReservationViewModelFactory(val model: ReservationModel) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationViewModel(model) as T
        }
    }
}