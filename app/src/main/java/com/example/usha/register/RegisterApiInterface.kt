package com.example.usha.register

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface RegisterApiInterface {
    @POST("/api/users/")
    fun register(@Body registerBody: RegisterBody): Call<Any>
}
data class RegisterBody(
    var name: String,
    var email: String,
    var password: String
)