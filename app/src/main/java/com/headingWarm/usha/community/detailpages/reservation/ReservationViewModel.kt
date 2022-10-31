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
import com.headingWarm.usha.community.model_community.Community
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationViewModel(var community: Community,
                           var mWebViewDialog: Dialog,
                           val ableTintList: ColorStateList,
                           val disableTintList: ColorStateList,
                           val navController: NavController) : ViewModel() {

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
//        Log.e("reservationBody", body.toString())
        service.postReservation("Bearer "+MyApplication.loginInfo.token, body).enqueue(object : Callback<ReservationResponse> {
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

    // viewModel에서 인자를 받기 위한 custom Factory
    class ReservationViewModelFactory(var community: Community,
                                      var  myWebViewDialog: Dialog,
                                      val ableTintList: ColorStateList,
                                      val disableTintList: ColorStateList,
                                      val navController: NavController) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationViewModel(community, myWebViewDialog, ableTintList, disableTintList, navController) as T
        }
    }
}