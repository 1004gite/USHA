package com.headingWarm.usha.community

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.community.item_community.Community
import com.headingWarm.usha.community.item_community.CommunityItem
import com.headingWarm.usha.community.item_community.GetCommunityInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityModel {
    var pages = 0
    var communityItems: MutableLiveData<List<Community>> = MutableLiveData()
    init{
        setCommunityItems()
    }

    fun setCommunityItems(){
        var service = MyApplication.retrofit.create(GetCommunityInterface::class.java)
        service.getCommunities()
            .enqueue(object : Callback<CommunityItem> {
                override fun onResponse(
                    call: Call<CommunityItem>,
                    response: Response<CommunityItem>
                ) {
                    if (response.body() == null) {
                        // for test
                        MyApplication.loginInfo.loginned = false
                    } else {
                        val cItem = response.body()!!
                        pages = cItem.pages
                        communityItems.value = cItem.communities
                    }
                }

                override fun onFailure(call: Call<CommunityItem>, t: Throwable) {
                    Log.e("getCommunitiesFailed", t.message.toString())
                }

            })
    }
}