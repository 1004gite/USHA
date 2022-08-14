package com.example.usha.community.model_community

data class Review(
    val _id: String,
    val comment: String,
    val createdAt: String,
    val name: String,
    val rating: Int,
    val updatedAt: String,
    val user: String
)