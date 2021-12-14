package com.example.mygarage.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarage.network.bookings.BookingResponse
import com.example.mygarage.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalendarViewModel(val repository: Repository) : ViewModel() {
    val dateBookingList: LiveData<BookingResponse>
        get() = repository.dateBooking

    fun getDateBooking(dateFrom:String,dateTo:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDateBooking(dateFrom,dateTo)
        }
    }

}