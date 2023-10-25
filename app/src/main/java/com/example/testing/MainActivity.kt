package com.example.testing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import  android.widget.EditText
import android.widget.Toast
import  android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.testing.databinding.ActivityMainBinding
import com.example.testing.ui.HomeActivity
import com.example.testing.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    lateinit var etEmail: EditText
    lateinit var etEmailContainer: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button
    lateinit var etForgetPassword: TextView

    @Inject
    lateinit var tokenManager: TokenManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailFocusedLister()
        passwordFocusedLister()



        if(tokenManager.getToken() != null){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        btnLogin = binding.btnLogin
        etEmail = binding.email
        etPass = binding.password
        btnLogin = binding.btnLogin
        etForgetPassword = binding.ForgetPassword

        btnLogin.setOnClickListener{
            GlobalScope.launch {
                login()
            }
        }

        etForgetPassword.setOnClickListener {
            val intent = Intent(this, ForgetPassword::class.java)
            startActivity(intent)
        }


    }

    private fun emailFocusedLister(){
        binding.email.setOnFocusChangeListener{_, focused ->
            if(!focused){
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.email.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
        }
        return  null
    }

    private fun passwordFocusedLister(){
        binding.password.setOnFocusChangeListener{_, focused ->
            if(!focused){
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.password.text.toString()
        if(passwordText.length < 8){
            return "Minimum 8 characters are required"
        }
        return  null
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
            response.body()?.let { Log.d("token", it.access) }

            response.body()?.let { tokenManager.saveToken(it.access) }
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            } else{
                runOnUiThread {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

    }
}