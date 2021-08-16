package com.munesh.letstrackandroidchallenge.networking.models.responses


import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("isRegistered")
    val isRegistered: Boolean?,
    @SerializedName("result")
    val result: Result?,
    @SerializedName("user_detail")
    val userDetail: UserDetail?
)