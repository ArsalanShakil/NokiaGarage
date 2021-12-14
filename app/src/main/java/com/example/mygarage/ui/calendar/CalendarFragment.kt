package com.example.mygarage.ui.calendar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygarage.R
import com.example.mygarage.databinding.FragmentCalendarBinding
import com.example.mygarage.databinding.FragmentEquipmentBinding
import com.example.mygarage.ui.equipment.EquipmentViewModel
import com.example.mygarage.ui.home.adapters.BookingRecyclerViewAdapter
import com.example.mygarage.ui.utils.ConnectivityCheck
import com.example.mygarage.ui.utils.InternetCheckDialog
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val calendarViewModel by viewModel<CalendarViewModel>()
    lateinit var dateBookingAdapter: DateBookingRecyclerViewAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val internetCheck =  ConnectivityCheck(requireContext())
        val internetCheckDialog = InternetCheckDialog(requireActivity())
        internetCheckDialog.startLoading()
        internetCheckDialog.isDismiss()
        val dateFrom = getDate(calendarView.date,"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val setDateFrom = dateFrom.toString().slice(0..10) + "00:00:00.000Z"
        val setDateTo = dateFrom.toString().slice(0..10) + "23:59:59.000Z"


        internetCheck.observe(viewLifecycleOwner,{
            if(it == true){
                internetCheckDialog.isDismiss()
                calendarViewModel.getDateBooking(setDateFrom,setDateTo)
                calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                    val dateTimeFrom = year.toString() + "-" + (month + 1).toString() + "-" + dayOfMonth.toString() + "T00:00:00.000Z"
                    val dateTimeTo = year.toString() + "-" + (month + 1).toString() + "-" + dayOfMonth.toString() + "T23:59:59.000Z"
                    val dateFrom = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateTimeFrom).toString()
                    val dateTo = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateTimeTo).toString()
                    calendarViewModel.getDateBooking(dateFrom,dateTo)
                }
            }
            else {
                internetCheckDialog.startLoading()
            }
        })
        calendarViewModel.dateBookingList.observe(viewLifecycleOwner,{
            Log.d("Get DATE", it.toString())
            if(it.isEmpty()) {
                noReservationTxt.visibility = View.VISIBLE
                dateBookingRecyclerview.visibility = View.GONE

            }
            else {
                noReservationTxt.visibility = View.GONE
                dateBookingRecyclerview.visibility = View.VISIBLE
                dateBookingAdapter = DateBookingRecyclerViewAdapter(requireContext(), it)
                dateBookingRecyclerview.adapter = dateBookingAdapter
                dateBookingRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        })

    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}