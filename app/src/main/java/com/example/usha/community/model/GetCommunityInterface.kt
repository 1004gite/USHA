package com.example.usha.community.model

import com.example.usha.logins.login.model.LoginBody
import com.example.usha.logins.login.model.LoginResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GetCommunityInterface {
    @GET("api/community/")
    fun getCommunities(): Call<CommunityItem>
}