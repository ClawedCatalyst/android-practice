package com.example.testing.api


import com.example.testing.ProfileDetailResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ProfileAPI{
    @GET("auth/get_profile_details/")
    suspend fun getProfileData(): Response<List<ProfileDetailResponseItem>>
}