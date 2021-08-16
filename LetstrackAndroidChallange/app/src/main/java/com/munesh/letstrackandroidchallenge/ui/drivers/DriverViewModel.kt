package com.munesh.letstrackandroidchallenge.ui.drivers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.munesh.letstrackandroidchallenge.networking.models.responses.Driver
import com.munesh.letstrackandroidchallenge.networking.models.responses.DriverResponseModel
import com.munesh.letstrackandroidchallenge.networking.retrofit.RetrofitService
import com.munesh.letstrackandroidchallenge.networking.retrofit.api_interfaces.RetrofitDriver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DriverViewModel : ViewModel() {
    private var driverApi: RetrofitDriver =
        RetrofitService.createService(RetrofitDriver::class.java)

    private var drivers = MutableLiveData<List<Driver>?>()

    fun fetchDriverList(customerId: String, token: String) {
        driverApi.getDriverList(token, customerId).enqueue(object : Callback<DriverResponseModel> {
            override fun onResponse(
                call: Call<DriverResponseModel>,
                response: Response<DriverResponseModel>
            ) {
                if (response.isSuccessful) {
                    val list = response.body()?.drivers
                    drivers.value = list
                } else {
                    drivers.value = null
                }
            }

            override fun onFailure(call: Call<DriverResponseModel>, t: Throwable) {
                drivers.value = null
            }
        })
    }

    fun getDriverList(): MutableLiveData<List<Driver>?> {
        return drivers
    }

}