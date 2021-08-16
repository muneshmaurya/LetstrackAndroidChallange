package com.munesh.letstrackandroidchallenge.networking.retrofit.api_interfaces

import com.munesh.letstrackandroidchallenge.networking.models.responses.DriverResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitDriver {
    @GET("api/MyDriverList")
    fun getDriverList(
        @Header("Authorization") authToken: String,
        @Query("customer_id") customerId: String
    ): Call<DriverResponseModel>
}