package com.example.internshipfinalinstagram.apis

import com.example.internshipfinalinstagram.models.AddPostResponse
import com.example.internshipfinalinstagram.models.AllPostsResponse
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.AuthResponse
import com.example.internshipfinalinstagram.models.In4UserResponse
import com.example.internshipfinalinstagram.models.LikeRequest
import com.example.internshipfinalinstagram.models.LikeResponse
import com.example.internshipfinalinstagram.models.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.File

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

    @GET("api/v1/list-post/{username}")
    fun getPostsOfUser(
        @Path ("username") username: String,
        @Query ("sort") sort: String,
        @Query ("page") page : Int,
        @Query ("perPage") perPage : Int
    ) : Call<AllPostsResponse>

    @Multipart
    @POST("api/v1/post")
    fun addPost(
        @Part ("userId") userId : RequestBody,
//        @Part images: List<File>,
        @Part images: List<MultipartBody.Part>,
        @Part ("content") content : RequestBody
    ) : Call<AddPostResponse>

    @POST("api/v1/like")
    fun likePost(@Body likeRequest: LikeRequest) : Call<LikeResponse>

    @GET("api/v1/user/{username}")
    fun getUserIn4(@Path ("username") username : String) : Call<In4UserResponse>
}