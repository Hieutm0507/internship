package com.example.internshipfinalinstagram.repositories

import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.LoginResponse
import retrofit2.Call

interface APIRepository {
    fun loginUser(loginRequest: LoginRequest) : Call<LoginResponse>
}