package com.example.mygarage.ui.reservations

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.ui.reservations.datetime.enddate.EndDatePickerFragment
import com.example.mygarage.ui.reservations.datetime.startdate.DatePickerFragment
import com.example.mygarage.ui.signin.SignInViewModel
import com.example.mygarage.ui.signup.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_reservations.*
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat

class ReservationsFragment : Fragment() {

    private val signInViewModel: SignInViewModel by sharedViewModel()
    private val signUpViewModel: SignUpViewModel by sharedViewModel()
    private var setId = ""
    private var setStartDate = ""
    private var setEndDate = ""
    val args : ReservationsFragmentArgs by navArgs()
    private val reservationsViewModel: ReservationsViewModel by koinNavGraphViewModel(R.id.reservationsFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reservation_layout.setBackgroundColor(Color.parseColor(args.color))
        Glide.with(requireContext()).load(args.imgUrl).into(reservation_img)
        reservation_tv.text = args.name
        pickStartDateBtn.setOnClickListener {
            showDatePickerDialog()
        }
        pickEndDateBtn.setOnClickListener {
            showEndDatePickerDialog()
        }

        reservation_btn.setOnClickListener {
            Log.d("setID", setId)
            reservationsViewModel.postBooking(setStartDate,setEndDate,args.name,setId,args.imgUrl)

        }

        reservationsViewModel.sendBooking.observe(viewLifecycleOwner,{
            Log.d("POST RESPONSE", it.toString())
            if (!reservationsViewModel.checkResponse(it.message)) {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            } else {
                findNavController().navigate(R.id.action_reservationsFragment_to_navigation_home)
            }
        })

        reservationsViewModel.startDateString.observe(viewLifecycleOwner,{
            pickStartDateBtn.text = it
            setStartDate = it
        })
        reservationsViewModel.endDateString.observe(viewLifecycleOwner,{
            pickEndDateBtn.text = it
            setEndDate = it
        })

        signInViewModel.signIn.observeForever {
            setId = it._id
        }
        signUpViewModel.signUp.observeForever {
            setId = it._id
        }


        if(args.homeToBooking) {
            reservation_btn.visibility = View.GONE
            editReservationBtn.visibility = View.VISIBLE
            deleteReservationsBtn.visibility = View.VISIBLE
            val dateFrom = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(args.dateFrom)
            var dateFromString = SimpleDateFormat("dd LLLL yyyy, HH:mm").format(dateFrom)
            val dateTo = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(args.dateTo)
            var dateToString = SimpleDateFormat("dd LLLL yyyy, HH:mm").format(dateTo)
            reservationsViewModel.startDateString.value = dateFromString
            reservationsViewModel.endDateString.value = dateToString

        }
        else {
            editReservationBtn.visibility = View.GONE
            deleteReservationsBtn.visibility = View.GONE
            reservation_btn.visibility = View.VISIBLE
            reservationsViewModel.startDateString.value = "Start date and time"
            reservationsViewModel.endDateString.value = "End date and time"
        }

        editReservationBtn.setOnClickListener {
            Log.d("setID", setId)
            reservationsViewModel.editBooking(args.bookingId,setStartDate,setEndDate,args.name,setId,args.imgUrl)
        }
        reservationsViewModel.editBooking.observe(viewLifecycleOwner,{
            Log.d("EDIT RESPONSE", it.toString())
            findNavController().navigate(R.id.action_reservationsFragment_to_navigation_home)
        })

        deleteReservationsBtn.setOnClickListener {
            reservationsViewModel.deleteBooking(args.bookingId)
        }
        reservationsViewModel.deleteBooking.observe(viewLifecycleOwner,{
            Log.d("DELETE RESPONSE", it.toString())
            findNavController().navigate(R.id.action_reservationsFragment_to_navigation_home)
        })
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }
    private fun showEndDatePickerDialog() {
        val newFragment = EndDatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "enddatePicker")
    }

}