package com.headingWarm.usha.community.detailpages.reservation

import android.app.Dialog
import android.content.res.ColorStateList
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.community.item_community.Community
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationModel(var community: Community, var mWebViewDialog: Dialog, val ableTintList: ColorStateList,
                       val disableTintList: ColorStateList, val navController: NavController) {
    var checkedTerms = MutableLiveData(false)
    var tintList: LiveData<ColorStateList> = checkedTerms.map { if(it) ableTintList else disableTintList }

    var userName = MutableLiveData(MyApplication.loginInfo.name)
    var userEmail = MutableLiveData(MyApplication.loginInfo.email)
    var userPhoneNumber = MutableLiveData("")

    fun showTermWebView(){
        mWebViewDialog.show()
    }

    fun requestJoinCommunity(){
        val service = MyApplication.retrofit.create(ReservationApiInterface::class.java)
        val body = ReservationRequestBody(
            userName.value!!,
            userEmail.value!!,
            userPhoneNumber.value!!,
            community._id,
            "credit card",
            community.price
        )
        service.postReservation("Bearer "+ MyApplication.loginInfo.token, body).enqueue(object :
            Callback<ReservationResponse> {
            override fun onResponse(
                call: Call<ReservationResponse>,
                response: Response<ReservationResponse>
            ) {
                if(response.code() == 201 || response.code() == 200){
                    // 요청 성공
                    MyApplication.toastPublisher.onNext("가입 성공")
                }
                else{
                    // 요청 실패
                }
                navController.popBackStack()
            }

            override fun onFailure(call: Call<ReservationResponse>, t: Throwable) {

            }
        })
    }
}