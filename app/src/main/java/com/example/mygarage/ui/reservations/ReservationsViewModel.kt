package com.example.mygarage.ui.reservations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class ReservationsViewModel : ViewModel() {

    private val date = Calendar.getInstance()
    var dateString = MutableLiveData("")
    private val dateFormatter = SimpleDateFormat("dd LLLL yyyy, HH:mm", Locale.getDefault())

    fun selectDate(day: Int, month: Int, year: Int) {
        date.set(Calendar.DAY_OF_MONTH, day)
        date.set(Calendar.MONTH, month)
        date.set(Calendar.YEAR, year)
        dateString.value = dateFormatter.format(date.time)
    }
}