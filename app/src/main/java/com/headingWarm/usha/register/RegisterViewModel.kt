package com.headingWarm.usha.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.headingWarm.usha.R

class RegisterViewModel(val model: RegisterModel): ViewModel() {

    // 양방향 버튼이기 때문에 그냥 viewModel.model로 데이터바인딩 함

    fun gotoLogin(){
        model.navController.navigate(R.id.login)
    }

    fun showTerm1(){
        model.termUrl.value = "https://www.headingwarm.com/term2"
    }

    fun showTerm2(){
        model.termUrl.value = "https://www.headingwarm.com/term"
    }

    fun clickBackBtn(){
        model.navController.popBackStack()
    }

    fun register(){
        model.regis()
    }

    class RegisterViewModelFac(val model: RegisterModel): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(model) as T
        }
    }
}