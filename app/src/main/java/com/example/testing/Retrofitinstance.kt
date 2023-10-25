package com.example.testing

import com.example.testing.api.Apiinterface
import com.example.testing.api.AuthInterceptor
import com.example.testing.api.ProfileAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class Retrofitinstance {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://awsbackend.suhaila.tech/mailer/")
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(authInterceptor: AuthInterceptor) : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideAPI(retrofitBuilder: Retrofit.Builder): Apiinterface{
        return retrofitBuilder.build().create(Apiinterface::class.java)
    }

    @Singleton
    @Provides
    fun provideProfileAPI(retrofitBuilder: Builder, okHttpClient: OkHttpClient): ProfileAPI{
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create(ProfileAPI::class.java)
    }

}