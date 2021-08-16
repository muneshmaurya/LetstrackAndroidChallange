package com.munesh.letstrackandroidchallenge.networking.retrofit.api_interfaces

import com.munesh.letstrackandroidchallenge.networking.models.requests.LoginRequestModel
import com.munesh.letstrackandroidchallenge.networking.models.responses.LoginResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitToken {
    @POST("api/Customerlogin")
    fun customerLogin(@Body request: LoginRequestModel) : Call<LoginResponseModel>
}