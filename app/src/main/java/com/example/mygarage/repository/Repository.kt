package com.example.mygarage.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygarage.network.ApiEndpointCalls
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.bookings.BookingResponse
import com.example.mygarage.network.equipments.EquipmentData
import com.example.mygarage.network.profile.ProfileResponse
import com.example.mygarage.network.signin.SignInData
import com.example.mygarage.network.signin.SignInResponse
import com.example.mygarage.network.signup.SignUpData
import com.example.mygarage.network.signup.SignUpResponse


class Repository(private val apiCall : ApiEndpointCalls) {


    private val articlesLiveData = MutableLiveData<ArticlesData>()
    val articles : LiveData<ArticlesData>
    get() = articlesLiveData

    private val equipmentLiveData = MutableLiveData<EquipmentData>()
    val equipment : LiveData<EquipmentData>
    get() = equipmentLiveData

    private val signInLiveData = MutableLiveData<SignInResponse>()
    val signIn : LiveData<SignInResponse>
    get() = signInLiveData

    private val signUpLiveData = MutableLiveData<SignUpResponse>()
    val signUp : LiveData<SignUpResponse>
        get() = signUpLiveData

    private val bookingsLiveData = MutableLiveData<BookingResponse>()
    val booking : LiveData<BookingResponse>
        get() = bookingsLiveData

    private val profileLiveData = MutableLiveData<ProfileResponse>()
    val profile : LiveData<ProfileResponse>
        get() = profileLiveData




     suspend fun getArticles() {
        val articleResult = apiCall.getArticles()
        if (articleResult?.body() != null) {
            articlesLiveData.postValue(articleResult.body())
        }
    }

    suspend fun getEquipments() {
        val equipmentResult = apiCall.getEquipment()
        if (equipmentResult?.body() != null) {
            equipmentLiveData.postValue(equipmentResult.body())
        }
    }


    suspend fun getSignIn(email:String,password:String) {
        val signInResponse = apiCall.getSignIn(SignInData(email,password))
        if (signInResponse?.body() != null) {
            signInLiveData.postValue(signInResponse.body())
        }
    }

    suspend fun getSignUp(email:String,fullName:String,password:String) {
        val signUpResponse = apiCall.getSignUp(SignUpData(email,fullName,password))
        if (signUpResponse?.body() != null) {
            signUpLiveData.postValue(signUpResponse.body())
        }
    }

    suspend fun getBooking(id : String) {
        val bookingResponse = apiCall.getBooking(id)
        if (bookingResponse?.body() != null) {
            bookingsLiveData.postValue(bookingResponse.body())
        }
    }

    suspend fun getProfile(id : String) {
        val profileResponse = apiCall.getProfile(id)
        if (profileResponse?.body() != null) {
            profileLiveData.postValue(profileResponse.body())
        }
    }
}