package com.example.mygarage.ui.signup


import android.util.Patterns
import androidx.lifecycle.*
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
    val isEnabled = MediatorLiveData<Boolean>()

    init {
        isEnabled.run {
            addSource(email) { email ->
                isEnabled.value =
                    getEnabled(
                        email,
                        fullName.value!!,
                        password.value!!
                    )
            }

            addSource(fullName) { fullName ->
                isEnabled.value =
                    getEnabled(
                        email.value!!,
                        fullName,
                        password.value!!,
                    )
            }

            addSource(password) { password ->
                isEnabled.value =
                    getEnabled(
                        email.value!!,
                        fullName.value!!,
                        password,
                    )
            }
        }
    }

    fun signUpBtnClick(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSignUp(email.value!!,fullName.value!!,password.value!!)
        }
    }

    fun getEnabled(
        email: String,
        fullName: String,
        password: String
    ): Boolean {
        return if (email.isNotBlank() && fullName.isNotBlank() && password.isNotBlank()) {
            email.isValidEmail() && fullName.isNotBlank() && password.isNotBlank()
        } else {
            false
        }

    }

    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

}