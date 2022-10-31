package com.headingWarm.usha.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.headingWarm.usha.MyApplication
import com.headingWarm.usha.community.model_community.Community
import com.headingWarm.usha.community.model_community.CommunityItem
import com.headingWarm.usha.community.model_community.GetCommunityInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityViewModel : ViewModel() {

    var pages = 0
    private var _communityItems: MutableLiveData<MutableList<LiveData<Community>>> = MutableLiveData(mutableListOf<LiveData<Community>>())
    val communityItems: LiveData<MutableList<LiveData<Community>>> get() = _communityItems

    fun setCommunityItems(){
        var tmp = mutableListOf<LiveData<Community>>()
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
                        for (community in cItem.communities) {
                            tmp.add(MutableLiveData<Community>(community))
                        }
                    }

                    _communityItems.value = tmp
                }

                override fun onFailure(call: Call<CommunityItem>, t: Throwable) {
                    Log.e("getCommunitiesFailed", t.message.toString())
                }

            })

    }
}