package com.munesh.letstrackandroidchallenge.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.munesh.letstrackandroidchallenge.R
import com.munesh.letstrackandroidchallenge.databinding.ActivityLoginBinding
import com.munesh.letstrackandroidchallenge.networking.models.requests.LoginRequestModel
import com.munesh.letstrackandroidchallenge.ui.drivers.DriversActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.password.error = getString(loginState.passwordError)
            }
        })

        viewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            binding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

        })

        binding.username.afterTextChanged {
            viewModel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.apply {
            afterTextChanged {
                viewModel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        performLogin(binding.username.text.toString(), binding.password.text.toString())
                }
                false
            }

            binding.login.setOnClickListener {
                binding.loading.visibility = View.VISIBLE
                performLogin(binding.username.text.toString(), binding.password.text.toString())
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun performLogin(userName: String, pswd: String) {
        val request = LoginRequestModel(
            userId = userName,
            countryCode = "91",
            languageCode = "en",
            password = pswd,
            deviceToken = "eODv9-7dSkmVQw-qpuZ0Pu:APA91bFur8SGKTx3j3GJ4OT6QXpdNekbUqyNJSBCH_OEAsgjjqX8R-hdYPydRp76P_ivPtO6CSFcw3_PjPDvQtwKCN1Bh91ls2TAqCEqlmODxBwsDTB2D1LGGO2c0M9AjF8ZNrCCKklh",
            deviceType = "0",
            brand = Build.MANUFACTURER,
            model = Build.MODEL,
            osVersion = Build.VERSION.SDK_INT,
            udId = Settings.Secure.getString(
                applicationContext.contentResolver,
                Settings.Secure.ANDROID_ID
            ),
            jailBroken = false
        )
        viewModel.login(request)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()

        //Complete and destroy login activity once successful
        val driversIntent = Intent(this, DriversActivity::class.java)
        driversIntent.putExtra("customerId",model.customerId)
        driversIntent.putExtra("token",model.token)
        startActivity(driversIntent)
        finish()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}