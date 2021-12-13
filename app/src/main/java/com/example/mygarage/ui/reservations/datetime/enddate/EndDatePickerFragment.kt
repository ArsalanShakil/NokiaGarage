package com.example.mygarage.ui.reservations.datetime.enddate

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.mygarage.R
import com.example.mygarage.ui.reservations.ReservationsViewModel
import com.example.mygarage.ui.reservations.datetime.startdate.TimePickerFragment
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class EndDatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private val viewModel: ReservationsViewModel by koinNavGraphViewModel(R.id.reservationsFragment)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        c.add(Calendar.DATE, 0) // set past calendar dates to 0

        // Create a new instance of DatePickerDialog and return datePickerDialog
        val datePickerDialog = DatePickerDialog(requireActivity(), this, year, month, day)
        val newDate = c.time
        // set minimum date that the user can select to today
        datePickerDialog.datePicker.minDate = newDate.time - newDate.time % (24 * 60 * 60 * 1000)
        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
        viewModel.selectEndDate(day,month,year)
        showTimePickerDialog()
    }
    private fun showTimePickerDialog() {
        val newFragment = EndTimePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "endtimePicker")
    }
}