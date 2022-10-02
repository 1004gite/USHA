package com.example.usha.register

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.usha.MyApplication
import com.example.usha.R
import com.example.usha.logins.login.model.LoginApiInterface
import com.example.usha.logins.login.model.LoginBody
import com.example.usha.logins.login.model.LoginResult
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RegisterViewModel: ViewModel() {

    lateinit var navController: NavController
    lateinit var dialogPublisher: PublishSubject<String>

    var name = MutableLiveData<String>("")
    var email = MutableLiveData<String>("")
    var pw = MutableLiveData<String>("")
    var pw2 = MutableLiveData<String>("")

    var check1 = MutableLiveData<Boolean>(false)
    var check3 = MutableLiveData<Boolean>(false)
    var check2 = MutableLiveData<Boolean>(false)

    val emailRegex = "^.+@.+$".toRegex()

    val service = MyApplication.retrofit.create(RegisterApiInterface::class.java)

    val termCheck: Boolean
        get() { return check1.value!! && check2.value!! && check3.value!!}

    val insertCheck: Boolean
        get() { return name.value != "" && emailRegex.matches(email.value!!) && pw.value != "" }

    fun clickBackBtn(){
        navController.popBackStack()
    }

    fun register(){
        if(!insertCheck){
            MyApplication.toastPublisher.onNext("모든 항목을 입력해 주세요!")
        }
        else if(pw.value != pw2.value){
            // 비밀번호 다름
            MyApplication.toastPublisher.onNext("비밀번호가 다릅니다.")
        }
        else if(!termCheck){
            MyApplication.toastPublisher.onNext("약관에 동의해주세요.")
        }
        else{
            // 가입 후 로그인 main페이지로
            service.register(RegisterBody(name.value!!, email.value!!, pw.value!!)).enqueue(object : Callback<Any> {
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

    fun gotoLogin(){
        navController.navigate(R.id.login)
    }

    fun showTerm1(){
        dialogPublisher.onNext("https://www.headingwarm.com/term2")
    }

    fun showTerm2(){
        dialogPublisher.onNext("https://www.headingwarm.com/term")
    }

}