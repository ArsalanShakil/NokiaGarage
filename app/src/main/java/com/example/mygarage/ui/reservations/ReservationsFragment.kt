package com.example.mygarage.ui.reservations


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygarage.databinding.FragmentReservationsBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class ReservationsFragment : Fragment() {

    private lateinit var reservationsViewModel: ReservationsViewModel
    private var _binding: FragmentReservationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reservationsViewModel =
            ViewModelProvider(this)[ReservationsViewModel::class.java]

        _binding = FragmentReservationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvDayPickBtn.setOnClickListener {
            showDateRangePicker()
        }
        binding.tvTimePickBtn.setOnClickListener {
            showTimeRangePicker()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Date picker

    private fun showDateRangePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select appointment date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(
                    CalendarConstraints.Builder()
                        .setValidator(DateValidatorPointForward.now()).build()
                )
                .build()

        datePicker.show(
            childFragmentManager,
            "date_range_picker"
        )

        datePicker.addOnPositiveButtonClickListener { dateSelected ->
            if (dateSelected != null) {
                binding.reservationTimeTv.text =
                    "Date Selected: ${convertLongToDay(dateSelected)}"
            }
        }
    }

    private fun convertLongToDay(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd.MM.yyyy HH.mm",
            Locale.getDefault()
        )
        return format.format(date)
    }

    // Time picker

    private fun showTimeRangePicker() {
        val isSystem24Hour = is24HourFormat(requireContext())
        val clockFormat = if(isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select appointment time")
            .build()

        timePicker.show(childFragmentManager, "time_range_picker")

        timePicker.addOnPositiveButtonClickListener { dateSelected ->
            if (dateSelected != null) {
            //    binding.tvTimePickBtn.text =
             //       "Date Selected: ${convertLongToDay(dateSelected)}"
                Log.d("tagg",dateSelected.toString())
            }

        }
    }
}
