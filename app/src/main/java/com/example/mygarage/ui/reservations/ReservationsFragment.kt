package com.example.mygarage.ui.reservations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mygarage.databinding.FragmentReservationsBinding
import com.google.android.material.datepicker.MaterialDatePicker

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
    ): View? {
        reservationsViewModel =
            ViewModelProvider(this)[ReservationsViewModel::class.java]

        _binding = FragmentReservationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reservationPickBtn.setOnClickListener {
            Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDataRangePicker() {
        val dateRangePicker =
            MaterialDatePicker
                .Builder.dateRangePicker()
                .setTitleText("Select Date")
                .build()

    }
}