package com.headingWarm.usha.logins

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.headingWarm.usha.R

class LogingMainViewModel : ViewModel() {

    lateinit var navController: NavController

    fun clickXbtn(){
        navController.popBackStack()
    }

    fun clickLoginBtn(){
        navController.navigate(R.id.login)
    }

    fun clickRegisterBtn(){
        navController.navigate(R.id.register)
    }

}