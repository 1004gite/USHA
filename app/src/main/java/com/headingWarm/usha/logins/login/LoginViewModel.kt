package com.headingWarm.usha.logins.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class LoginViewModel(val model: LoginModel) : ViewModel() {

    val loginClickable: LiveData<Boolean> get() = model.loginClickable

    fun gotoRegister() {
        model.navController.navigate(R.id.register)
    }

    fun findPw(){
        model.navController.navigate(R.id.findpw)
    }

    fun clickLoginBtn(){
        model.login()
    }


    class LoginVMFac(val model: LoginModel): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(model) as T
        }
    }

}