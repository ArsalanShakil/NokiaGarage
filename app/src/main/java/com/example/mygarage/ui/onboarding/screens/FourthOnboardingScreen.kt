package com.example.mygarage.ui.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.mygarage.R
import kotlinx.android.synthetic.main.fragment_fourth_onboarding_screen.view.*


class FourthOnboardingScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fourth_onboarding_screen, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.startTxt.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_signInFragment)
            onBoardingFinished()
        }

        view.txtPrevious4.setOnClickListener {
            if (viewPager != null) {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }

        return view
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}