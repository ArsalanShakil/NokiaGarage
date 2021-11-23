package com.example.mygarage.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygarage.network.articles.Articles
import com.example.mygarage.network.articles.ArticlesApi
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.equipments.EquipmentApi
import com.example.mygarage.network.equipments.EquipmentData


class Repository(private val articlesApi : ArticlesApi, private val equipmentApi: EquipmentApi) {
    private val articlesLiveData = MutableLiveData<ArticlesData>()
    val articles : LiveData<ArticlesData>
    get() = articlesLiveData

     suspend fun getArticles() {
        val articleResult = articlesApi.getArticles()
        if (articleResult?.body() != null) {
            articlesLiveData.postValue(articleResult.body())
        }
    }

    private val equipmentLiveData = MutableLiveData<EquipmentData>()
    val equipment : LiveData<EquipmentData>
        get() = equipmentLiveData

    suspend fun getEquipments() {
        val equipmentResult = equipmentApi.getEquipment()
        if (equipmentResult?.body() != null) {
            equipmentLiveData.postValue(equipmentResult.body())
        }
    }

}