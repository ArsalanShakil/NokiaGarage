package com.example.mygarage.network.bookings

data class BookingData(
    val dateTimeFrom: String,
    val dateTimeTo: String,
    val name: String,
    val ownerUserId: String,
    val url: String
)
