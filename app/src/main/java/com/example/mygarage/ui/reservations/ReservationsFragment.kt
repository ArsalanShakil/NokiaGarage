package com.example.mygarage.ui.reservations

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mygarage.R
import com.example.mygarage.ui.reservations.datetime.DatePickerFragment
import com.example.mygarage.ui.reservations.datetime.TimePickerFragment
import com.example.mygarage.ui.signin.SignInViewModel
import com.example.mygarage.ui.signup.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_article_details.*
import kotlinx.android.synthetic.main.fragment_reservations.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReservationsFragment : Fragment() {

    private val reservationsViewModel: ReservationsViewModel by sharedViewModel()
    private val signInViewModel: SignInViewModel by sharedViewModel()
    private val signUpViewModel: SignUpViewModel by sharedViewModel()
    private var setId = ""
    val args : ReservationsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reservationImg = args.imgUrl
        val reservationName = args.name
        val reservatioColor = args.color

        reservation_layout.setBackgroundColor(Color.parseColor(reservatioColor))
        Glide.with(requireContext()).load(reservationImg).into(reservation_img)
        reservation_tv.text = reservationName
        day_pick_btn.setOnClickListener {
            showDatePickerDialog()
        }
        time_pick_btn.setOnClickListener {
            showTimePickerDialog()
        }

        reservationsViewModel.dateString.observe(viewLifecycleOwner,{
            reservation_time_tv.text = it
        })


        signInViewModel.signIn.observeForever {
            setId = it._id
        }
        signUpViewModel.signUp.observeForever {
            setId = it._id
        }
        reservation_btn.setOnClickListener {
            Log.d("setID", setId)
            reservationsViewModel.postBooking("2021-01-22T11:12:00.000Z","2021-01-23T11:30:00.000Z",reservationName,setId,reservationImg)

            reservationsViewModel.sendBooking.observe(viewLifecycleOwner,{
                Log.d("POST RESPONSE", it.toString())

                if (!reservationsViewModel.checkResponse(it.message)){
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                } else {
                    findNavController().navigate(R.id.action_reservationsFragment_to_navigation_home)

                }
            })
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

    private fun showTimePickerDialog() {
        val newFragment = TimePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }
}