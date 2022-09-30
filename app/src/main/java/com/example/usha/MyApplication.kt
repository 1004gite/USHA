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

        @JvmStatic
        fun logoutFunc(): Boolean{
            loginInfo.loginned = false
            prefs.setString(Consts.token, "no")
            return loginInfo.loginned
        }
    }

    override fun onCreate() {
        prefs = PrefsManager(applicationContext)
        loginInfo = LoginInfo().apply {
            if(prefs.getString(Consts.token, "no") != "no"){
                name = prefs.getString(Consts.name,"")
                email = prefs.getString(Consts.email,"")
                token = prefs.getString(Consts.token,"")
                loginned = true
                isAdmin = prefs.getString(Consts.isAdmin,"") == "true"
                passWord = prefs.getString(Consts.password, "")
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
    val name = "name"
    val email = "email"
    val password = "password"
    val token = "token"
    val isAdmin = "isAdmin"
    val baseURL = "http://ushabackend-env.eba-xwidq8fh.us-east-1.elasticbeanstalk.com"
    val reservationTermUrl = "http://www.naver.com"
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