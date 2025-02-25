package com.example.internshipfinalinstagram.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Khởi tạo RETROFIT

object ApiClient {
    private const val BASE_URL = "https://insta.hoibai.net/"

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()           // Create an instance of Retrofit
            .addConverterFactory(GsonConverterFactory.create())         // Converter
            .baseUrl(BASE_URL)              // Base URL of API
            .build()
    }

    fun getApi() : IgAPI {
        return getRetrofit().create(IgAPI::class.java)
    }
}