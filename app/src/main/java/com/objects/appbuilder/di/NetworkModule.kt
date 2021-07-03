package com.objects.appbuilder.di





import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



object NetworkModule {

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
        }.build()
    }

    fun provideRetrofit(
        baseUrl:String
    ): Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl(baseUrl).build()
    }

    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}