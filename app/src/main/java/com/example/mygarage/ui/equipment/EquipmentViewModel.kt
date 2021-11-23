package com.example.mygarage.ui.equipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.equipments.EquipmentData
import com.example.mygarage.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipmentViewModel(val repository: Repository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getEquipments()
        }
    }
    val equipmentList: LiveData<EquipmentData>
        get() = repository.equipment
}