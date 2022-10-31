package com.headingWarm.usha.community.model_community

data class Review(
    val _id: String,
    val comment: String,
    val createdAt: String,
    val name: String,
    val rating: Int,
    val updatedAt: String,
    val user: String
){
    override fun toString(): String {
        return "id: $_id, comment: $comment, createAt: $createdAt, name: $name, rating: $rating, updatedAt: $updatedAt, user: $user\n"
    }
}