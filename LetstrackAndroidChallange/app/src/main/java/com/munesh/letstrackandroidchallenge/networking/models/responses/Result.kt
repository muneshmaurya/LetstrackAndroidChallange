package com.munesh.letstrackandroidchallenge.networking.models.responses


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("msg")
    val msg: String?
)