package com.example.mygarage.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygarage.network.ApiEndpointCalls
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.equipments.EquipmentData
import com.example.mygarage.network.signin.SignInData
import com.example.mygarage.network.signin.SignInResponse


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


    suspend fun getSignIn(username:String,password:String) {
        val signInResponse = apiCall.getSignIn(SignInData(username,password))
        if (signInResponse?.body() != null) {
            signInLiveData.postValue(signInResponse.body())
        }
    }
}