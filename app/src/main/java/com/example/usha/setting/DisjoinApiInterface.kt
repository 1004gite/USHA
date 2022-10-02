package com.example.usha.setting

import retrofit2.Call
import retrofit2.http.*

interface DisjoinApiInterface {
    @DELETE("api/users/{id}")
//    @HTTP(method="DELETE", hasBody=true, path="api/users/{id}")
    fun deleteUser(@Header("Authorization") Token: String, @Path("id") id:String): Call<Any>
}
//data class DeleteUserResponse(
//    var test: String
//)
//
//data class DeleteUserBody(
//    var name: String,
//    var email: String,
//    var phone: String,
//    var communityItems: String,
//    var paymentMethod: String,
//    var itemPrice: String
//    )
