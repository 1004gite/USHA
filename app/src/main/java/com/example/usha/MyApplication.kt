package com.example.usha

import android.app.Application
import android.content.Context
import com.example.usha.logins.Model.LoginInfo

class MyApplication : Application() {
    companion object {
        var Consts = com.example.usha.MyConsts()
        lateinit var prefs: PrefsManager
        lateinit var loginInfo: LoginInfo
    }

    override fun onCreate() {
        prefs = PrefsManager(applicationContext)
        loginInfo = LoginInfo().apply {
            if(prefs.getString(Consts.email, "noEmail") != "noEmail"){
                // 로그인 시도 후 자동 로그인 할 것
            }
        }
        super.onCreate()
    }


}

class MyConsts(){
    var email = "email"
    var password = "password"
    var token = "token"
}

class PrefsManager(context: Context) {
    private val prefs = context.getSharedPreferences("USHA_pref", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String) : String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }
}