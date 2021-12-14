package com.example.mygarage.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygarage.network.ApiEndpointCalls
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.bookings.BookingData
import com.example.mygarage.network.bookings.BookingResponse
import com.example.mygarage.network.bookings.BookingResponseItem
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

    private val sendBookingLiveData = MutableLiveData<BookingResponseItem>()
    val sendBooking : MutableLiveData<BookingResponseItem>
        get() = sendBookingLiveData

    private val deleteBookingLiveData = MutableLiveData<BookingResponseItem>()
    val deleteBooking : LiveData<BookingResponseItem>
        get() = deleteBookingLiveData

    private val editBookingLiveData = MutableLiveData<BookingResponseItem>()
    val editBooking : LiveData<BookingResponseItem>
        get() = editBookingLiveData

    private val dateBookingsLiveData = MutableLiveData<BookingResponse>()
    val dateBooking : LiveData<BookingResponse>
        get() = dateBookingsLiveData

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

    suspend fun getDateBooking(dateFrom : String,dateTo : String) {
        val dateBookingResponse = apiCall.getDateBooking(dateFrom,dateTo)
        if (dateBookingResponse?.body() != null) {
            dateBookingsLiveData.postValue(dateBookingResponse.body())
        }
    }

    suspend fun getProfile(id : String) {
        val profileResponse = apiCall.getProfile(id)
        if (profileResponse?.body() != null) {
            profileLiveData.postValue(profileResponse.body())
        }
    }


    suspend fun sendBooking(dateTimeFrom: String,dateTimeTo: String,name: String,ownerUserId: String,url: String) {
        val bookingResponse = apiCall.sendBooking(BookingData(dateTimeFrom,dateTimeTo,name,ownerUserId,url))
        if (bookingResponse?.body() != null) {
            sendBookingLiveData.postValue(bookingResponse.body())
        }
    }

    suspend fun deleteBooking(bookingId : String) {
        val deleteBookingResponse = apiCall.deleteBooking(bookingId)
        if (deleteBookingResponse?.body() != null) {
            deleteBookingLiveData.postValue(deleteBookingResponse.body())
        }
    }

    suspend fun editBooking(bookingId : String,dateTimeFrom: String,dateTimeTo: String,name: String,ownerUserId: String,url: String) {
        val editBookingResponse = apiCall.editBooking(bookingId,BookingData(dateTimeFrom,dateTimeTo,name,ownerUserId,url))
        if (editBookingResponse?.body() != null) {
            editBookingLiveData.postValue(editBookingResponse.body())
        }
    }
}