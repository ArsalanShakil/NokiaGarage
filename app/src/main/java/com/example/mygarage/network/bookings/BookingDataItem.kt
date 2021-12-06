package com.example.mygarage.network.bookings

data class BookingDataItem(
    val dateTimeFrom: String,
    val dateTimeTo: String,
    val name: String,
    val url: String
)