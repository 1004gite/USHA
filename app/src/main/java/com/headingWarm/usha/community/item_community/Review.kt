package com.headingWarm.usha.community.item_community

import java.io.Serializable

data class Review(
    val _id: String,
    val comment: String,
    val createdAt: String,
    val name: String,
    val rating: Int,
    val updatedAt: String,
    val user: String
): Serializable{
    override fun toString(): String {
        return "id: $_id, comment: $comment, createAt: $createdAt, name: $name, rating: $rating, updatedAt: $updatedAt, user: $user\n"
    }
}