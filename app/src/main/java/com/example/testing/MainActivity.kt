package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import  android.widget.EditText
import android.widget.Toast
import  android.widget.Button
import kotlinx.coroutines.launch
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.email)
        etPass = findViewById(R.id.password)

        btnLogin.setOnClickListener {
            GlobalScope.launch {
                login()
            }
        }
    }

    private suspend fun login(){
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        var requestObj = request(email, pass)
        Log.d("request", requestObj.toString())
        val response = Retrofitinstance.apiInterface.login(requestObj)
        Log.d("suhail", response.body().toString())
        if (response.code() == 201) {
                runOnUiThread {
                    Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                }
            } else{
                runOnUiThread {
                    Toast.makeText(this, "LoggedIn failed", Toast.LENGTH_SHORT).show()
                }
            }

    }
}