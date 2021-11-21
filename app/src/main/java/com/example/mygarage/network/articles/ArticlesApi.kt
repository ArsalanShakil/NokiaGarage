package com.example.mygarage.network.articles

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://users.metropolia.fi/~arsalans/"

interface ArticlesApi {
    @GET("articles")
    fun getArticles():Call<ArticlesData>
}

object Articles {
    val articlesInstance: ArticlesApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        articlesInstance = retrofit.create(ArticlesApi::class.java)
    }
}
