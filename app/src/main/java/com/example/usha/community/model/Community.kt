package com.example.usha.community.model

import androidx.lifecycle.LiveData
import java.io.Serializable

data class Community(
    val __v: Int,
    val _id: String,
    val coach: String,
    val community_img: String,
    val createdAt: String,
    val curriculum_img: String,
    val description: String,
    val endDate: String,
    val goalName: String,
    val goalTerm: String,
    val hashTags: List<String>,
    val introduce_img: String,
    val location: String,
    val mentor_img: String,
    val name: String,
    val price: String,
    val reviews: List<Review>,
    val rule1: String,
    val rule1_sub: String,
    val rule2: String,
    val rule2_sub: String,
    val startDate: String,
    val updatedAt: String,
    val user: String,
) : Serializable{
    val tagString: String get() {
        var tmp = "#"
        for(tag in hashTags) tmp += " $tag"
        return tmp
    }

    val periodString: String get() {
        return startDate.substring(0,9)+" ~ "+endDate.substring(0,9)
    }
}