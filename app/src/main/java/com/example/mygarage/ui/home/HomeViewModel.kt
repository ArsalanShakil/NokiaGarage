package com.example.mygarage.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.bookings.BookingResponse
import com.example.mygarage.network.signin.SignInResponse
import com.example.mygarage.network.signup.SignUpResponse
import com.example.mygarage.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repository: Repository) : ViewModel() {

    //val id = MutableLiveData("")
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getArticles()
        }
    }
    val articleList: LiveData<ArticlesData>
    get() = repository.articles

    val bookingList: LiveData<BookingResponse>
        get() = repository.booking

    val signUp: LiveData<SignUpResponse>
        get() = repository.signUp
    val signIn: LiveData<SignInResponse>
        get() = repository.signIn
    fun getUserBookings(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBooking(id)
        }
    }
}