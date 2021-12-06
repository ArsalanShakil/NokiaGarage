package com.example.mygarage.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarage.network.signin.SignInResponse
import com.example.mygarage.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(private val repository: Repository) : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val signIn: LiveData<SignInResponse>
        get() = repository.signIn
    fun signInBtnClick(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSignIn(email.value!!,password.value!!)
        }
    }
    fun checkResponse(id:String?):Boolean {
        return id.isNullOrEmpty()
    }
}