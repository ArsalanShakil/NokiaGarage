package com.example.mygarage.ui.reservations.datetime

import android.app.Dialog
import androidx.fragment.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import com.example.mygarage.ui.reservations.ReservationsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private val viewModel: ReservationsViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)


        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }


    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int){
        // Do something with the time chosen by the user
        viewModel.selectHourMinute(hourOfDay,minute)

    }


}