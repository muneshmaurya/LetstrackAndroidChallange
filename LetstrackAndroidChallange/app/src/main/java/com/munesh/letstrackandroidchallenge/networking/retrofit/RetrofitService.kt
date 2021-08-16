package com.munesh.letstrackandroidchallenge.networking.retrofit

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RetrofitService {
    private const val BASE_URL = "http://whapidev.centralus.cloudapp.azure.com/CustomerStaging/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}