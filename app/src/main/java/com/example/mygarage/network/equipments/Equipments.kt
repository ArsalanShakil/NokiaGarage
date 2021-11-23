package com.example.mygarage.network.equipments

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Equipments {
    private const val BASE_URL = "https://users.metropolia.fi/~arsalans/"
    fun equipmentInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}