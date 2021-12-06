package com.example.mygarage.network.signup

data class SignUpResponse(
    val __v: Int,
    val _id: String,
    val email: String,
    val fullName: String,
    val passwordHash: String
)