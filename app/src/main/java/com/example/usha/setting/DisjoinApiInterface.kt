package com.example.usha.setting

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface DisjoinApiInterface {
    @DELETE("api/users/{id}")
    fun deleteUser(@Path("id") id:String, @Body deleteUserBody: DeleteUserBody): Call<DeleteUserResponse>
}
data class DeleteUserResponse(
    var test: String
)

data class DeleteUserBody(
    var name: String,
    var email: String,
    var phone: String,
    var communityItems: String,
    var paymentMethod: String,
    var itemPrice: String
    )


//interface LoginApiInterface {
//    @POST("/api/users/login")
//    fun getLoginResult(@Body loginBody: LoginBody): Call<LoginResult>
//}
//
//data class LoginResult(
//    var _id: String,
//    var name: String,
//    var email: String,
//    var isAdmin: Boolean,
//    var token: String,
//)
//
//data class LoginBody(
//    var email: String,
//    var password: String
//)