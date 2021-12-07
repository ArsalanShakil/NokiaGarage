package com.example.mygarage.ui.reservations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarage.network.bookings.BookingResponse
import com.example.mygarage.network.bookings.BookingResponseItem
import com.example.mygarage.network.profile.ProfileResponse
import com.example.mygarage.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ReservationsViewModel(val repository: Repository) : ViewModel() {
    val sendBooking: LiveData<BookingResponseItem>
        get() = repository.sendBooking

    fun postBooking(dateTimeFrom: String,dateTimeTo: String,name: String,ownerUserId: String,url: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendBooking(dateTimeFrom,dateTimeTo,name,ownerUserId,url)
        }
    }
    private val date = Calendar.getInstance()
    var dateString = MutableLiveData("")
    private val dateFormatter = SimpleDateFormat("dd LLLL yyyy, HH:mm", Locale.getDefault())

    fun selectDate(day: Int, month: Int, year: Int) {
        date.set(Calendar.DAY_OF_MONTH, day)
        date.set(Calendar.MONTH, month)
        date.set(Calendar.YEAR, year)
        dateString.value = dateFormatter.format(date.time)
    }

    fun selectHourMinute(hour: Int, minute: Int) {
        date.set(Calendar.HOUR_OF_DAY, hour)
        date.set(Calendar.MINUTE, minute)
        date.set(Calendar.SECOND, 0)
        dateString.value = dateFormatter.format(date.time)
    }
}