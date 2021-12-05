package com.example.mygarage.network.signin

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiEndpoint {
    @POST("login")
    suspend fun getSignIn(@Body params : SignInData): Response<SignInResponse>
}