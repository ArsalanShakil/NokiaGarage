package com.example.mygarage.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.profile.ProfileResponse
import com.example.mygarage.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    val profle: LiveData<ProfileResponse>
        get() = repository.profile

    fun setText(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProfile(id)
        }
    }
}