package com.example.internshipfinalinstagram.repositories

import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.AuthResponse
import com.example.internshipfinalinstagram.models.RegisterRequest
import retrofit2.Call

interface APIRepository {
    fun loginUser(loginRequest: LoginRequest, callback: (Result<AuthResponse>) -> Unit) : Call<AuthResponse>
}