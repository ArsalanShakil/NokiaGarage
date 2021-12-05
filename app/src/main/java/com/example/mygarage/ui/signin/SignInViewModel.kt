package com.example.mygarage.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarage.network.signin.SignInResponse
import com.example.mygarage.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(val repository: Repository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSignIn()
        }
    }
    val signIn: LiveData<SignInResponse>
        get() = repository.signIn
}