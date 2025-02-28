package com.example.internshipfinalinstagram.repositories

import com.example.internshipfinalinstagram.models.AllPostsResponse
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.AuthResponse
import com.example.internshipfinalinstagram.models.RegisterRequest
import retrofit2.Call

interface APIRepository {
    fun loginUser(loginRequest: LoginRequest, callback: (Result<AuthResponse>?) -> Unit) : Call<AuthResponse>

    fun registerUser(registerRequest: RegisterRequest, callback: (Result<AuthResponse>) -> Unit) : Call<AuthResponse>

    suspend fun getAllPosts(sort: String, page: Int, perPage: Int, callback: (AllPostsResponse?) -> Unit)

}