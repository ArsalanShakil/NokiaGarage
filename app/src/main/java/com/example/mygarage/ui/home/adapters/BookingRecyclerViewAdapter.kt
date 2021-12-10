package com.example.mygarage.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.network.bookings.BookingResponseItem
import com.example.mygarage.ui.home.HomeFragmentDirections
import com.example.mygarage.ui.home.HomeViewModel
import com.example.mygarage.ui.home.viewholders.BookingRecyclerViewHolder
import com.example.mygarage.ui.signin.SignInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat


class BookingRecyclerViewAdapter(val context: Context, val bookings: List<BookingResponseItem>) : RecyclerView.Adapter<BookingRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.booking_recycler_item,parent, false)
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
        holder.itemView.setOnClickListener{ view ->
            val action = HomeFragmentDirections.actionNavigationHomeToReservationsFragment("#D3E1FF",booking.url,booking.name,true,booking._id,booking.dateTimeFrom,booking.dateTimeTo)
            view.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return bookings.size
    }


}