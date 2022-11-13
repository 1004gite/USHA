package com.headingWarm.usha.setting

import retrofit2.Call
import retrofit2.http.*

interface DisjoinApiInterface {
    @DELETE("api/users/{id}")
    fun deleteUser(@Header("Authorization") Token: String, @Path("id") id:String): Call<Any>
}