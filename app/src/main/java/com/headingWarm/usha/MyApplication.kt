package com.headingWarm.usha

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.headingWarm.usha.logins.Model.LoginInfo
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    companion object {
        // 앱 전역적으로 쓰이는 기능들
        lateinit var prefs: PrefsManager
        lateinit var loginInfo: LoginInfo
        lateinit var retrofit: Retrofit

        @JvmStatic
        fun logoutFunc(): Boolean{
            loginInfo.loginned = false
            prefs.setString(Consts.token, "no")
            return loginInfo.loginned
        }

        lateinit var toastPublisher: PublishSubject<String>
    }

    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        prefs = PrefsManager(applicationContext)
        loginInfo = LoginInfo().apply {
            if(prefs.getString(Consts.token, "no") != "no"){
                name = prefs.getString(Consts.name,"")
                email = prefs.getString(Consts.email,"")
                token = prefs.getString(Consts.token,"")
                loginned = true
                isAdmin = prefs.getString(Consts.isAdmin,"") == "true"
                passWord = prefs.getString(Consts.password, "")
                id = prefs.getString(Consts.id, "")
            }
        }
        retrofit = Retrofit.Builder()
            .baseUrl(Consts.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        super.onCreate()
    }


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