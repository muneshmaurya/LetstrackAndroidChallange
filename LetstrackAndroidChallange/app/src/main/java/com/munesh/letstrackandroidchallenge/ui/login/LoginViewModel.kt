package com.munesh.letstrackandroidchallenge.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.munesh.letstrackandroidchallenge.R
import com.munesh.letstrackandroidchallenge.networking.models.requests.LoginRequestModel
import com.munesh.letstrackandroidchallenge.networking.models.responses.LoginResponseModel
import com.munesh.letstrackandroidchallenge.networking.retrofit.RetrofitService
import com.munesh.letstrackandroidchallenge.networking.retrofit.api_interfaces.RetrofitToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private var loginApi: RetrofitToken = RetrofitService.createService(RetrofitToken::class.java)

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(request: LoginRequestModel) {

        loginApi.customerLogin(request).enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                if (response.isSuccessful) {
                    _loginResult.value =
                        LoginResult(
                            success = LoggedInUserView(
                                displayName = response.body()?.userDetail?.firstname!!,
                                customerId = response.body()?.userDetail?.customerId!!,
                                token = response.body()?.userDetail?.token!!
                            )
                        )
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        })

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}