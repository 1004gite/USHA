package com.headingWarm.usha.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.headingWarm.usha.community.item_community.Community

class CommunityViewModel : ViewModel() {

    val model = CommunityModel()
    val communityItems: LiveData<List<Community>> get() = model.communityItems
}