package com.example.testing

import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

interface Apiinterface {
    @POST("auth/login/")
    suspend fun login(@Body loginRequest: request): Response<response>
}