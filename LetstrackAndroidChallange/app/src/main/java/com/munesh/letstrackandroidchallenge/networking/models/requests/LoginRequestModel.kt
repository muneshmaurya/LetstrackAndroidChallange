package com.munesh.letstrackandroidchallenge.networking.models.requests


import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("countryCode")
    val countryCode: String?,
    @SerializedName("deviceToken")
    val deviceToken: String?,
    @SerializedName("deviceType")
    val deviceType: String?,
    @SerializedName("jailBroken")
    val jailBroken: Boolean?,
    @SerializedName("languageCode")
    val languageCode: String?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("osVersion")
    val osVersion: Int?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("udId")
    val udId: String?,
    @SerializedName("userId")
    val userId: String?
)