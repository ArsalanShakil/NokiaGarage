package com.example.mygarage.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.network.bookings.BookingResponseItem
import com.example.mygarage.ui.home.viewholders.BookingRecyclerViewHolder


class BookingRecyclerViewAdapter(val context: Context, val bookings: List<BookingResponseItem>) : RecyclerView.Adapter<BookingRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.booking_recycler_item,parent, false)
        return BookingRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingRecyclerViewHolder, position: Int) {
        val booking = bookings[position]
        holder.bookingTitle.text = booking.name
        holder.bookingFromTitle.text = booking.dateTimeFrom
        holder.bookingToTitle.text = booking.dateTimeTo
        Glide.with(context).load(booking.url).into(holder.bookingImage)
        holder.itemView.setOnClickListener{ view ->
        }
    }

    override fun getItemCount(): Int {
        return bookings.size
    }


}