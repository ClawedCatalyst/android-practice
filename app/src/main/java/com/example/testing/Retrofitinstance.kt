package com.example.testing

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofitinstance {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://awsbackend.suhaila.tech/mailer/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface by lazy {
        retrofit.create(Apiinterface::class.java)
    }
}