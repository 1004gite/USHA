package com.headingWarm.usha.profile

import androidx.navigation.NavController
import com.headingWarm.usha.MyApplication.Companion.loginInfo
import com.headingWarm.usha.R

class ProfileModel(val navController: NavController) {

    val name = loginInfo.name
    val email = loginInfo.email

    fun goToSettingFrag(){
        navController.navigate(R.id.action_profile_to_setting)
    }
}