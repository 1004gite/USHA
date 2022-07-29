package com.example.usha

import android.util.Log
import com.example.usha.logins.login.model.LoginApiInterface
import com.example.usha.logins.login.model.LoginBody
import com.example.usha.logins.login.model.LoginResult
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun clickLoginBtn() {
        // 로그인
        var loop = true
        var retrofit = Retrofit.Builder()
            .baseUrl(MyApplication.Consts.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginApiInterface::class.java)
        val loginBody = LoginBody("user@user.com", "12345678")
        service.getLoginResult(loginBody).enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                println(response.body().toString())
                loop = false
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                println(t.message!!)
                loop = false
            }

        })
        while(loop){

        }
    }
}