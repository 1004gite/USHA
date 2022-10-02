package com.example.usha.logins.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.usha.MyApplication
import com.example.usha.R
import com.example.usha.logins.login.model.LoginApiInterface
import com.example.usha.logins.login.model.LoginBody
import com.example.usha.logins.login.model.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    var emailString = MutableLiveData<String>("")
    var pwString = MutableLiveData<String>("")
    var _loginClickAble = MutableLiveData<Boolean>(true)
    val loginClickAble: LiveData<Boolean> get() = _loginClickAble
    lateinit var navController: NavController
    val service = MyApplication.retrofit.create(LoginApiInterface::class.java)

    fun clickLoginBtn() {
        _loginClickAble.value = false
        // 예외처리
        if (emailString.value!!.length < 2 || pwString.value!!.length < 2) {
            _loginClickAble.value = true
            return
        }
        // 로그인
        val loginBody = LoginBody(emailString.value!!, pwString.value!!)
        service.getLoginResult(loginBody).enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                if(response.body() == null){
                    // 로그인 실패
                    MyApplication.toastPublisher.onNext("로그인 실패")
                    MyApplication.loginInfo.loginned = false
                }
                else{
                    val loginResult= response.body()!!
                    MyApplication.prefs.setString(MyApplication.Consts.email, loginBody.email)
                    MyApplication.prefs.setString(MyApplication.Consts.password, loginBody.password)
                    MyApplication.prefs.setString(MyApplication.Consts.token, loginResult.token)
                    MyApplication.prefs.setString(MyApplication.Consts.isAdmin, loginResult.isAdmin.toString())
                    MyApplication.prefs.setString(MyApplication.Consts.name, loginResult.name)
                    MyApplication.prefs.setString(MyApplication.Consts.id, loginResult._id)

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

                _loginClickAble.value = true
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                Log.e("fail",t.message!!)
                MyApplication.loginInfo.loginned = false
                _loginClickAble.value = true
            }

        })
    }

}