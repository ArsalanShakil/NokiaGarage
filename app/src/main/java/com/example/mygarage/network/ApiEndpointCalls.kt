package com.example.mygarage.network

import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.equipments.EquipmentData
import com.example.mygarage.network.signin.SignInData
import com.example.mygarage.network.signin.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndpointCalls {
    @GET("articles")
    suspend fun getArticles(): Response<ArticlesData>

    @GET("equipments")
    suspend fun getEquipment(): Response<EquipmentData>

    @POST("login")
    suspend fun getSignIn(@Body params : SignInData): Response<SignInResponse>

}