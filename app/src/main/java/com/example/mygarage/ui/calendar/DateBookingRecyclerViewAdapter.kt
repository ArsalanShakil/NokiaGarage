package com.example.mygarage.ui.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.network.bookings.BookingResponseItem
import com.example.mygarage.ui.home.HomeFragmentDirections
import com.example.mygarage.ui.home.viewholders.BookingRecyclerViewHolder
import java.text.SimpleDateFormat

class DateBookingRecyclerViewAdapter(val context: Context, val bookings: List<BookingResponseItem>) : RecyclerView.Adapter<BookingRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.calendar_booking_recycler_item,parent, false)
        return BookingRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingRecyclerViewHolder, position: Int) {
        val booking = bookings[position]
        holder.bookingTitle.text = booking.name
        val dateFrom = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(booking.dateTimeFrom)
        var dateFromString = SimpleDateFormat("dd LLLL yyyy, HH:mm").format(dateFrom)
        val dateTo = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(booking.dateTimeTo)
        var dateToString = SimpleDateFormat("dd LLLL yyyy, HH:mm").format(dateTo)
        holder.bookingFromTitle.text = dateFromString
        holder.bookingToTitle.text = dateToString
        Glide.with(context).load(booking.url).into(holder.bookingImage)
    }

    override fun getItemCount(): Int {
        return bookings.size
    }


}