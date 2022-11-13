package com.headingWarm.usha.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import com.headingWarm.usha.MyApplication
import kotlinx.coroutines.flow.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterModel(val navController: NavController) {

    var termUrl: MutableLiveData<String> = MutableLiveData()

    var name = MutableLiveData<String>("")
    var email = MutableLiveData<String>("")
    var pw = MutableLiveData<String>("")
    var pw2 = MutableLiveData<String>("")

    var check1 = MutableLiveData<Boolean>(false)
    var check3 = MutableLiveData<Boolean>(false)
    var check2 = MutableLiveData<Boolean>(false)

    val emailRegex = "^.+@.+$".toRegex()

    val service = MyApplication.retrofit.create(RegisterApiInterface::class.java)

    val termCheck = combine(check1.asFlow(),check2.asFlow(),check3.asFlow()) {
        a,b,c -> a&&b&&c }.asLiveData()
    val insertCheck = combine(name.asFlow(),email.asFlow(),pw.asFlow()){
        n,e,pw -> n.isNotBlank()&&emailRegex.matches(e)&&pw.isNotBlank() }.asLiveData()
    val registerClickable: LiveData<Boolean> = combine(termCheck.asFlow(),insertCheck.asFlow()){
        a,b -> a&&b }.asLiveData()

    fun checkForRegis(): Boolean{
        if(insertCheck.value != true){
            MyApplication.toastPublisher.onNext("모든 항목을 입력해 주세요!")
            return false
        }
        if(pw.value != pw2.value){
            // 비밀번호 다름
            MyApplication.toastPublisher.onNext("비밀번호가 다릅니다.")
            return false
        }
        if(termCheck.value != true){
            MyApplication.toastPublisher.onNext("약관에 동의해주세요.")
            return false
        }
        return true
    }

    fun regis(){
        // 가입 후 로그인 main페이지로
//        if(!checkForRegis()) return
        service.register(RegisterBody(name.value!!, email.value!!, pw.value!!)).enqueue(object :
            Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.e("registerResult", response.message())
                navController.popBackStack()
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                MyApplication.toastPublisher.onNext("가입 실패")
            }
        })
    }
}