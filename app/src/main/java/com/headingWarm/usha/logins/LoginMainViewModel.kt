package com.headingWarm.usha.logins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.headingWarm.usha.R
import com.headingWarm.usha.logins.login.LoginViewModel

class LoginMainViewModel(val navController: NavController) : ViewModel() {

    fun clickXbtn(){
        navController.popBackStack()
    }

    fun clickLoginBtn(){
        navController.navigate(R.id.login)
    }

    fun clickRegisterBtn(){
        navController.navigate(R.id.register)
    }

    class LoginMainVMFac(val navController: NavController): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginMainViewModel(navController) as T
        }
    }

}