package com.example.testing.api

import com.example.testing.forgetPasswordRequest
import com.example.testing.forgetPasswordResponse
import com.example.testing.request
import com.example.testing.response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.Response

interface Apiinterface {
    @POST("auth/login/")
    suspend fun login(@Body loginRequest: request): Response<response>

    @POST("auth/otp_reset_password/")
    suspend fun forgetPasswordOTP(@Body forgetPasswordRequest: forgetPasswordRequest): Response<forgetPasswordResponse>

}