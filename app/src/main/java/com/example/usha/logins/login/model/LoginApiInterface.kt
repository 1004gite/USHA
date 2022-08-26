package com.example.usha.logins.login.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiInterface {
    @POST("/api/users/login")
    fun getLoginResult(@Body loginBody: LoginBody): Call<LoginResult>
}

data class LoginResult(
    var _id: String,
    var name: String,
    var email: String,
    var isAdmin: Boolean,
    var token: String,
)

data class LoginBody(
    var email: String,
    var password: String
)