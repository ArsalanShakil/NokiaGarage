package com.example.mygarage.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygarage.network.articles.ArticlesApi
import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.equipments.EquipmentApi
import com.example.mygarage.network.equipments.EquipmentData
import com.example.mygarage.network.signin.ApiEndpoint
import com.example.mygarage.network.signin.SignInData
import com.example.mygarage.network.signin.SignInResponse


class Repository(private val articlesApi : ArticlesApi, private val equipmentApi: EquipmentApi, private val signInApi: ApiEndpoint) {
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
        val articleResult = articlesApi.getArticles()
        if (articleResult?.body() != null) {
            articlesLiveData.postValue(articleResult.body())
        }
    }

    suspend fun getEquipments() {
        val equipmentResult = equipmentApi.getEquipment()
        if (equipmentResult?.body() != null) {
            equipmentLiveData.postValue(equipmentResult.body())
        }
    }


    suspend fun getSignIn() {
        val signInResponse = signInApi.getSignIn(SignInData("abc@123.com","sometext"))
        if (signInResponse?.body() != null) {
            signInLiveData.postValue(signInResponse.body())
        }
    }
}