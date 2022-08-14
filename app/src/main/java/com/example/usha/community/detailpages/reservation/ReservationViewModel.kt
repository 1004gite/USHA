package com.example.usha.community.detailpages.reservation

import android.app.Dialog
import android.content.res.ColorStateList
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usha.MyApplication
import com.example.usha.community.model_community.Community
import com.example.usha.logins.login.model.LoginApiInterface
import com.example.usha.logins.login.model.LoginBody
import com.example.usha.logins.login.model.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationViewModel(var community: Community, var mWebViewDialog: Dialog, val ableTintList: ColorStateList, val disableTintList: ColorStateList) : ViewModel() {

    // 변화 없이 읽기만 할거면 LiveData를 쓸 필요가 없을듯..
//    private var _communityName = MutableLiveData(community!!.name)
//    val communityName: LiveData<String> get() = _communityName
//
//    private var _communityTag = MutableLiveData(community!!.tagString)
//    val communityTag: LiveData<String> get() = _communityTag
//
//    private var _communityPeriod = MutableLiveData(community!!.periodString)
//    val communityPeriod: LiveData<String> get() = _communityPeriod

    private var _checkedTerms = MutableLiveData(false)
    val checkedTerms: LiveData<Boolean> get() = _checkedTerms
    private var _tintList = MutableLiveData(disableTintList)
    val tintList: LiveData<ColorStateList> get() = _tintList

    var userName = MutableLiveData(MyApplication.loginInfo.name)
    var userEmail = MutableLiveData(MyApplication.loginInfo.email)
    var userPhoneNumber = MutableLiveData("")

    fun showTermWebView(){
        mWebViewDialog.show()
    }

    fun clickTermsCheck(cBox: View){
        _checkedTerms.value = (cBox as CheckBox).isChecked
        _tintList.value = if(_checkedTerms.value!!) ableTintList else disableTintList
    }

    // viewModel에서 인자를 받기 위한 custom Factory
    class ReservationViewModelFactory(var community: Community,var  myWebViewDialog: Dialog, val ableTintList: ColorStateList, val disableTintList: ColorStateList) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationViewModel(community, myWebViewDialog, ableTintList, disableTintList) as T
        }
    }

    fun clickJoinCommunity(){
        // 입력을 잘 했는지 예외처리 해도 될듯 여기서
        val service = MyApplication.retrofit.create(ReservationApiInterface::class.java)
        val body = ReservationRequestBody(
            userName.value!!,
            userEmail.value!!,
            userPhoneNumber.value!!,
            community._id,
            "credit card", // ???
            community.price
        )
        service.postReservation(body).enqueue(object : Callback<ReservationResponse> {
            override fun onResponse(
                call: Call<ReservationResponse>,
                response: Response<ReservationResponse>
            ) {
                Log.e("success", response.body().toString())
            }

            override fun onFailure(call: Call<ReservationResponse>, t: Throwable) {

            }
        })
    }
}