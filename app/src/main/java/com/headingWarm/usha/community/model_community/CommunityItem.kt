package com.headingWarm.usha.community.model_community

data class CommunityItem(
    val communities: List<Community>,
    val page: Int,
    val pages: Int
)