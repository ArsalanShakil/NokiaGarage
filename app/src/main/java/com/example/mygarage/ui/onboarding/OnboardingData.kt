package com.example.mygarage.ui.onboarding

import androidx.annotation.DrawableRes

data class OnboardingData(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)
