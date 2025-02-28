package com.example.internshipfinalinstagram.repositories

import android.util.Log
import com.example.internshipfinalinstagram.apis.ApiClient
import com.example.internshipfinalinstagram.apis.IgAPI
import com.example.internshipfinalinstagram.models.AllPostsResponse
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.AuthResponse
import com.example.internshipfinalinstagram.models.In4UserResponse
import com.example.internshipfinalinstagram.models.LikeRequest
import com.example.internshipfinalinstagram.models.LikeResponse
import com.example.internshipfinalinstagram.models.RegisterRequest
import com.example.internshipfinalinstagram.models.AddPostResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class APIRepositoryImpl(private val apiService: IgAPI) : APIRepository {
    override fun loginUser(
        loginRequest: LoginRequest,
        callback: (Result<AuthResponse>?) -> Unit
    ): Call<AuthResponse> {
        ApiClient.getApi().loginUser(loginRequest).enqueue(object : Callback<AuthResponse> {

            // Khi connect tới API thành công
            // Hướng làm: Sử dụng callback khi move từ LoginViewModel sang APIRepositoryImpl
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.i(APIRepositoryImpl::class.java.simpleName, "onResponse: code: $it")

                        if (it.code == 400 || it.code == 404) {
                            callback(Result.failure(Exception(it.message)))
                        } else {
                            callback(Result.success(it))
                        }
                    }
                } else {
                    callback(Result.failure(Exception("Login failed with code: ${response.code()}")))
                }
            }

            // Khi connect tới API failed
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                callback(Result.failure(t))
            }
        })

        return ApiClient.getApi().loginUser(loginRequest)
    }

    override fun registerUser(
        registerRequest: RegisterRequest,
        callback: (Result<AuthResponse>) -> Unit
    ): Call<AuthResponse> {
        ApiClient.getApi().registerUser(registerRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>?, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.code == 400) {
                            callback(Result.failure(Exception(it.message)))
                        } else {
                            callback(Result.success(it))
                        }
                    }
                } else {
                    callback(Result.failure(Exception("Register failed with code: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<AuthResponse>?, t: Throwable) {
                callback(Result.failure(t))
            }
        })
        return ApiClient.getApi().registerUser(registerRequest)
    }

    override suspend fun getAllPosts(
        sort: String,
        page: Int,
        perPage: Int,
        callback: (AllPostsResponse?) -> Unit
    ) {
        var dataAfterGAP: AllPostsResponse ?= null
//        ApiClient.getApi().getAllPost(sort, page, perPage)
//            .enqueue(object : Callback<AllPostsResponse> {
//                override fun onResponse(
//                    call: Call<AllPostsResponse>,
//                    response: Response<AllPostsResponse>
//                ) {
//                    dataAfterGAP = response.body()
//                    callback(dataAfterGAP)
//                    Log.d("TAG_POST_TEST", response.body().toString())
//                }
//
//                override fun onFailure(call: Call<AllPostsResponse>?, t: Throwable) {
//                    t.printStackTrace()
//                    callback(dataAfterGAP)
//                    return
//                }
//            })
        CoroutineScope(Dispatchers.IO).launch {
            apiService.getAllPost(sort, page, perPage)
                .enqueue(object : Callback<AllPostsResponse>{
                    override fun onResponse(
                        call: Call<AllPostsResponse>,
                        response: Response<AllPostsResponse>
                    ) {
                        dataAfterGAP = response.body()
                        callback(dataAfterGAP)
                    }

                    override fun onFailure(call: Call<AllPostsResponse>, t: Throwable) {
                        t.printStackTrace()
                        callback(dataAfterGAP)
                        return
                    }
                })
        }
    }

    override fun getPostsOfUser(
        username: String,
        sort: String,
        page: Int,
        perPage: Int,
        callback: (AllPostsResponse?) -> Unit
    ) {
        var dataAfterGPU: AllPostsResponse ?= null

        CoroutineScope(Dispatchers.IO).launch {
            apiService.getPostsOfUser(username, sort, page, perPage)
                .enqueue(object : Callback<AllPostsResponse>{
                    override fun onResponse(
                        call: Call<AllPostsResponse>,
                        response: Response<AllPostsResponse>
                    ) {
                        dataAfterGPU = response.body()
                        callback(dataAfterGPU)
                    }

                    override fun onFailure(call: Call<AllPostsResponse>, t: Throwable) {
                        t.printStackTrace()
                        callback(dataAfterGPU)
                        return
                    }
                })
        }
    }

    override fun addPost(
        userId: String,
        imageFiles: List<File>,
        content: String,
        callback: (AddPostResponse?) -> Unit
    ) {
        var dataAfterAP: AddPostResponse ?= null

        val requestUserId = userId.toRequestBody("text/plain".toMediaTypeOrNull())
        val requestContent = content.toRequestBody("text/plain".toMediaTypeOrNull())

        val imageParts = imageFiles.map { file ->
            val requestFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("images", file.name, requestFile)
        }

        Log.d("DEBUG2API", "Ta có: ID: $requestUserId, IMAGE: $imageParts, CONTENT: $requestContent")

        CoroutineScope(Dispatchers.IO).launch {
            apiService.addPost(requestUserId, imageParts, requestContent)
                .enqueue(object : Callback<AddPostResponse>{
                    override fun onResponse(
                        call: Call<AddPostResponse>,
                        response: Response<AddPostResponse>
                    ) {
                        Log.d("DEBUG2API", "Response Code: ${response.code()}")
                        Log.d("DEBUG2API", "Response Body: ${response.body()}")
                        if (!response.isSuccessful) {
                            Log.e("DEBUG2API", "Error Body: ${response.errorBody()?.string()}")
                        }
                        dataAfterAP = response.body()
                        callback(dataAfterAP)
                    }

                    override fun onFailure(call: Call<AddPostResponse>, t: Throwable) {
                        t.printStackTrace()
                        callback(dataAfterAP)
                        return
                    }
                })
        }
    }


//    private fun fileListToMultipart(files: List<File>): List<MultipartBody.Part> {
//        return files.map { file ->
//            val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
//            MultipartBody.Part.createFormData("files", file.name, requestFile)
//        }
//    }

    override fun likePost(
        likeRequest: LikeRequest,
        callback: (LikeResponse?) -> Unit
    ) {
        var dataLikePost: LikeResponse ?= null

        CoroutineScope(Dispatchers.IO).launch {
            apiService.likePost(likeRequest)
                .enqueue(object : Callback<LikeResponse>{
                    override fun onResponse(
                        call: Call<LikeResponse>,
                        response: Response<LikeResponse>
                    ) {
                        dataLikePost = response.body()
                        callback(dataLikePost)
                    }

                    override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                        t.printStackTrace()
                        callback(dataLikePost)
                        return
                    }
                })
        }
    }

    override fun getUserIn4(username: String, callback : (In4UserResponse?) -> Unit) {
        var dataAfterGUI : In4UserResponse? = null
        CoroutineScope(Dispatchers.IO).launch {
            apiService.getUserIn4(username).enqueue(object : Callback<In4UserResponse>{
                override fun onResponse(
                    call: Call<In4UserResponse>?,
                    response: Response<In4UserResponse>?
                ) {
                    dataAfterGUI = response!!.body()
                    callback(dataAfterGUI)
                }

                override fun onFailure(call: Call<In4UserResponse>?, t: Throwable) {
                    t.printStackTrace()
                    callback(dataAfterGUI)
                }
            })
        }
    }
}