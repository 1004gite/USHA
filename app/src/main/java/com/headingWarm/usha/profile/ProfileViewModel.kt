package com.headingWarm.usha.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileViewModel(val model: ProfileModel): ViewModel() {

    val name: String get() = model.name
    val email: String get() = model.email


    fun clickSettingBtn(){
        model.goToSettingFrag()
    }


    class ProfileVMFac(val model: ProfileModel): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfileViewModel(model) as T
        }
    }
}