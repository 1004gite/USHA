package com.headingWarm.usha.community.model_community

import retrofit2.Call
import retrofit2.http.GET

interface GetCommunityInterface {
    @GET("api/community/")
    fun getCommunities(): Call<CommunityItem>
}