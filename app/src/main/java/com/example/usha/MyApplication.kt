package com.example.usha

import android.app.Application
import android.content.Context
import com.example.usha.logins.Model.LoginInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    companion object {
        var Consts = com.example.usha.MyConsts()
        lateinit var prefs: PrefsManager
        lateinit var loginInfo: LoginInfo
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        prefs = PrefsManager(applicationContext)
        loginInfo = LoginInfo().apply {
            if(prefs.getString(Consts.token, "no") != "no"){
                loginInfo.email = prefs.getString(Consts.email,"")
                loginInfo.token = prefs.getString(Consts.token,"")
                loginInfo.loginned = true
                loginInfo.isAdmin = prefs.getString(Consts.isAdmin,"") == "true"
                loginInfo.passWord = prefs.getString(Consts.password, "")
            }
        }
        retrofit = Retrofit.Builder()
            .baseUrl(Consts.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        super.onCreate()
    }


}

class MyConsts {
    val email = "email"
    val password = "password"
    val token = "token"
    val isAdmin = "isAdmin"
    val baseURL = "http://ushabackend-env.eba-xwidq8fh.us-east-1.elasticbeanstalk.com"
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