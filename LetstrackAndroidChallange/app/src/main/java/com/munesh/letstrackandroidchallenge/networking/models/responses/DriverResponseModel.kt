package com.munesh.letstrackandroidchallenge.networking.models.responses


import com.google.gson.annotations.SerializedName

data class DriverResponseModel(
    @SerializedName("record")
    val drivers: List<Driver>?,
    @SerializedName("result")
    val result: Result?
)