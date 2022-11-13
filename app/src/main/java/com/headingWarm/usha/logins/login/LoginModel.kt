package com.headingWarm.usha.logins.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.headingWarm.usha.Consts
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.R
import com.headingWarm.usha.logins.login.model.LoginApiInterface
import com.headingWarm.usha.logins.login.model.LoginBody
import com.headingWarm.usha.logins.login.model.LoginResult
import kotlinx.coroutines.flow.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel(val navController: NavController) {

    var emailString = MutableLiveData<String>("")
    var pwString = MutableLiveData<String>("")
    val service = MyApplication.retrofit.create(LoginApiInterface::class.java)
    val loginClickable: LiveData<Boolean> = combine(emailString.asFlow(), pwString.asFlow()){
        e,p -> e.isNotBlank()&&p.isNotBlank()
    }.asLiveData()

    fun login() {
        // 로그인
        val loginBody = LoginBody(emailString.value!!, pwString.value!!)
        // 로그인 연속으로 시도하는 것 방지
        pwString.value = ""
        service.getLoginResult(loginBody).enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                if(response.body() == null){
                    // 로그인 실패
                    MyApplication.toastPublisher.onNext("로그인 실패")
                    MyApplication.loginInfo.loginned = false
                }
                else{
                    val loginResult= response.body()!!
                    MyApplication.prefs.setString(Consts.email, loginBody.email)
                    MyApplication.prefs.setString(Consts.password, loginBody.password)
                    MyApplication.prefs.setString(Consts.token, loginResult.token)
                    MyApplication.prefs.setString(Consts.isAdmin, loginResult.isAdmin.toString())
                    MyApplication.prefs.setString(Consts.name, loginResult.name)
                    MyApplication.prefs.setString(Consts.id, loginResult._id)

                    MyApplication.loginInfo.email = loginBody.email
                    MyApplication.loginInfo.passWord = loginBody.password
                    MyApplication.loginInfo.loginned = true
                    MyApplication.loginInfo.isAdmin = loginResult.isAdmin
                    MyApplication.loginInfo.token = loginResult.token
                    MyApplication.loginInfo.name = loginResult.name
                    MyApplication.loginInfo.id = loginResult._id

                    // 로그인시 loginMain까지 백스택에서 제외하고 profile로 이동
                    var navops: NavOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.loginMain,true).build()
                    navController.navigate(R.id.profile,null,navops)
                }
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                Log.e("fail",t.message!!)
                MyApplication.loginInfo.loginned = false
            }

        })
    }
}