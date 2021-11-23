package com.example.mygarage.network.equipments

import retrofit2.Response
import retrofit2.http.GET

interface EquipmentApi {
    @GET("equipment")
    suspend fun getEquipment(): Response<EquipmentData>
}