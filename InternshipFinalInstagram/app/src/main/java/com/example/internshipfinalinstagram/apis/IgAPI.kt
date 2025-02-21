package com.example.internshipfinalinstagram.apis

import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.AuthResponse
import com.example.internshipfinalinstagram.models.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IgAPI {
    @POST("api/v1/login")
    fun loginUser(@Body loginRequest : LoginRequest) : Call<AuthResponse>
}