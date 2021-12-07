package com.example.mygarage.network.bookings

data class BookingResponseItem(
    val __v: Int,
    val _id: String,
    val dateTimeFrom: String,
    val dateTimeTo: String,
    val name: String,
    val ownerUserId: String,
    val url: String,
    val message: String?
)