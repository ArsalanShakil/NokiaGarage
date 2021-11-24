package com.example.mygarage.ui.reservations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygarage.R
import com.example.mygarage.ui.reservations.datetime.DatePickerFragment
import kotlinx.android.synthetic.main.fragment_reservations.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReservationsFragment : Fragment() {

    private val viewModel: ReservationsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        day_pick_btn.setOnClickListener {
            showDatePickerDialog()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

}