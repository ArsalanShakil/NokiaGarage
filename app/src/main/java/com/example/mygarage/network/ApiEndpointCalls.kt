package com.example.mygarage.network

import com.example.mygarage.network.articles.ArticlesData
import com.example.mygarage.network.bookings.BookingData
import com.example.mygarage.network.bookings.BookingResponse
import com.example.mygarage.network.bookings.BookingResponseItem
import com.example.mygarage.network.equipments.EquipmentData
import com.example.mygarage.network.profile.ProfileResponse
import com.example.mygarage.network.signin.SignInData
import com.example.mygarage.network.signin.SignInResponse
import com.example.mygarage.network.signup.SignUpData
import com.example.mygarage.network.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiEndpointCalls {
    @GET("articles")
    suspend fun getArticles(): Response<ArticlesData>

    @GET("equipments")
    suspend fun getEquipment(): Response<EquipmentData>

    @POST("login")
    suspend fun getSignIn(@Body params : SignInData): Response<SignInResponse>

    @POST("users")
    suspend fun getSignUp(@Body params : SignUpData): Response<SignUpResponse>

    @GET("bookings/users/{ownerId}")
    suspend fun getBooking(
        @Path("ownerId") id : String
    ): Response<BookingResponse>
    //61a8da3dfcd193094cc902f6

    @GET("bookings/date/{dateFrom}&{dateTo}")
    suspend fun getDateBooking(
        @Path("dateFrom") dateFrom : String,
        @Path("dateTo") dateTo : String
    ): Response<BookingResponse>

    @GET("users/{userId}")
    suspend fun getProfile(
        @Path("userId") id : String
    ): Response<ProfileResponse>

    @POST("bookings")
    suspend fun sendBooking(@Body params : BookingData): Response<BookingResponseItem>

    @DELETE("bookings/{bookingId}")
    suspend fun deleteBooking(
        @Path("bookingId") bookingId : String
    ): Response<BookingResponseItem>

    @PUT("bookings/{bookingId}")
    suspend fun editBooking(@Path("bookingId") bookingId : String,@Body params : BookingData): Response<BookingResponseItem>

}