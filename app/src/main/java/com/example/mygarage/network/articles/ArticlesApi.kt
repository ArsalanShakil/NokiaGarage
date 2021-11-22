package com.example.mygarage.network.articles

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ArticlesApi {
    @GET("articles")
    suspend fun getArticles():Response<ArticlesData>
}

