package com.example.usha.community.model

import android.graphics.Bitmap
import java.io.Serializable

data class CommunityItemModel(
    // 나중에 retrofit2 로 바꿀 예정
    val title: String,
    val tag: String,
    val data: String,
    val place: String,
    val coach: String
) : Serializable