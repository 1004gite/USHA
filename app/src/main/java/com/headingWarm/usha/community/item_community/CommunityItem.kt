package com.headingWarm.usha.community.item_community

data class CommunityItem(
    val communities: List<Community>,
    val page: Int,
    val pages: Int
)