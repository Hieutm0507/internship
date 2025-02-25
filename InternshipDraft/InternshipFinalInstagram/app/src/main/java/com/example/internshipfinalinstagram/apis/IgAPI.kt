package com.example.internshipfinalinstagram.apis

import com.example.internshipfinalinstagram.models.AllPostsResponse
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.AuthResponse
import com.example.internshipfinalinstagram.models.RegisterRequest
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface IgAPI {
    @POST("api/v1/login")
    fun loginUser(@Body loginRequest : LoginRequest) : Call<AuthResponse>

    @POST("api/v1/signup")
    fun registerUser(@Body registerRequest: RegisterRequest) : Call<AuthResponse>

    @GET("api/v1/list-post")
    fun getAllPost(
        @Query ("sort") sort : String,
        @Query ("page") page : Int,
        @Query ("perPage") perPage : Int)
    : Call<AllPostsResponse>
}