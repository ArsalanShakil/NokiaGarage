package com.example.mygarage.ui.calendar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygarage.R



class DateBookingRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var bookingTitle = itemView.findViewById<TextView>(R.id.bookingNameTxt)
    var bookingImage = itemView.findViewById<ImageView>(R.id.bookingImg)
    var bookingFromTitle = itemView.findViewById<TextView>(R.id.bookingFromTxt)
    var bookingToTitle = itemView.findViewById<TextView>(R.id.bookingToTxt)

}