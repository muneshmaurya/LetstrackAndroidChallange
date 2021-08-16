package com.munesh.letstrackandroidchallenge.networking.models.responses


import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("driver_id")
    val driverId: Long?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("mobileNo")
    val mobileNo: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("perHourPrice")
    val perHourPrice: Int?,
    @SerializedName("userId")
    val userId: Long?,
    @SerializedName("whideCode")
    val whideCode: String?,
    @SerializedName("whideTrust")
    val whideTrust: Double?
)