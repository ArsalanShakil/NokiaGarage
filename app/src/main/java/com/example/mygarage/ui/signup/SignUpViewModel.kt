package com.example.mygarage.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarage.network.signup.SignUpResponse
import com.example.mygarage.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: Repository) : ViewModel() {
     val email = MutableLiveData("")
     val fullName = MutableLiveData("")
     val password = MutableLiveData("")
    val signUp: LiveData<SignUpResponse>
        get() = repository.signUp

    fun signUpBtnClick(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSignUp(email.value!!,fullName.value!!,password.value!!)
        }
    }
}