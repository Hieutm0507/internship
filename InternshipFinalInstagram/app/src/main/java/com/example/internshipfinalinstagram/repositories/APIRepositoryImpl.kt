package com.example.internshipfinalinstagram.repositories

import com.example.internshipfinalinstagram.apis.ApiClient
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.LoginResponse
import retrofit2.Call

class APIRepositoryImpl : APIRepository {
    override fun loginUser(loginRequest: LoginRequest): Call<LoginResponse> {
        return ApiClient.getApi().loginUser(loginRequest)
    }
}