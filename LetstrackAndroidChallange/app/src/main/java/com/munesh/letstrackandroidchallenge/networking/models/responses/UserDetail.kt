package com.munesh.letstrackandroidchallenge.networking.models.responses


import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("bolt")
    val bolt: Double?,
    @SerializedName("brand")
    val brand: Any?,
    @SerializedName("country_code")
    val countryCode: Int?,
    @SerializedName("customer_id")
    val customerId: String?,
    @SerializedName("device_token")
    val deviceToken: Any?,
    @SerializedName("devicetype")
    val devicetype: Int?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("gender")
    val gender: Any?,
    @SerializedName("inviteCode")
    val inviteCode: Any?,
    @SerializedName("language_code")
    val languageCode: String?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("mobileno")
    val mobileno: String?,
    @SerializedName("model")
    val model: Any?,
    @SerializedName("osVersion")
    val osVersion: Any?,
    @SerializedName("otp")
    val otp: Any?,
    @SerializedName("password")
    val password: Any?,
    @SerializedName("profilepic_url")
    val profilepicUrl: String?,
    @SerializedName("qrCodeUrl")
    val qrCodeUrl: String?,
    @SerializedName("socket_token")
    val socketToken: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("whideCode")
    val whideCode: String?
)