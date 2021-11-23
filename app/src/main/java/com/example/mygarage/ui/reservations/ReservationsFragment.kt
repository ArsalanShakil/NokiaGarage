package com.example.mygarage.ui.reservations


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygarage.databinding.FragmentReservationsBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

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
        binding.reservationPickBtn.setOnClickListener {
            showDataRangePicker()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDataRangePicker() {
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
                    "Date Selected: ${convertLongToTime(dateSelected)}"
            }
        }
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault()
        )
        return format.format(date)
    }
}
