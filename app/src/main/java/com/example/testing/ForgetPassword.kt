package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.testing.api.Apiinterface
import com.example.testing.databinding.ActivityForgetPasswordBinding
import com.example.testing.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ForgetPassword : AppCompatActivity() {

    lateinit var forgetPasswordEmail: EditText
    lateinit var btnForgetPassword: Button

    @Inject
    lateinit var apiinterface: Apiinterface
    private lateinit var binding: ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        forgetPasswordEmail = binding.forgetPasswordemail
        btnForgetPassword = binding.btnForgetPassword

        btnForgetPassword.setOnClickListener{
            GlobalScope.launch {
                forgetPassword()
            }
        }


    }

    suspend fun forgetPassword(){
        val email = forgetPasswordEmail.text.toString()
        var requestObj = forgetPasswordRequest(email)
        Log.d("forget password request", requestObj.toString())
        val response = apiinterface.forgetPasswordOTP(requestObj)

        Log.d("forget password", response.body().toString())

        if (response.code() == 201) {
            runOnUiThread {
                Toast.makeText(this, "Please check you email", Toast.LENGTH_SHORT).show()
            }
        } else{
            runOnUiThread {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

    }
}