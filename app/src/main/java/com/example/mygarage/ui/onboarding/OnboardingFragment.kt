package com.example.mygarage.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mygarage.R
import com.example.mygarage.ui.onboarding.screens.FirstOnboardingScreen
import com.example.mygarage.ui.onboarding.screens.SecondOnboardingScreen
import com.example.mygarage.ui.onboarding.screens.ThirdOnboardingScreen
import com.example.mygarage.ui.onboarding.screens.FourthOnboardingScreen
import kotlinx.android.synthetic.main.fragment_onboarding.view.*

class OnboardingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstOnboardingScreen(),
            SecondOnboardingScreen(),
            ThirdOnboardingScreen(),
            FourthOnboardingScreen()
        )

        val adapter = OnboardingPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter

        return view
    }

}