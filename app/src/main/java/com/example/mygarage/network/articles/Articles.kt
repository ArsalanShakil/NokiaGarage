package com.example.mygarage.network.articles

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Articles {
    private const val BASE_URL = "https://users.metropolia.fi/~arsalans/"
    fun articlesInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}