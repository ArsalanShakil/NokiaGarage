package com.example.mygarage.network.profile

data class ProfileResponse(
    val __v: Int,
    val _id: String,
    val email: String,
    val fullName: String,
    val passwordHash: String
)