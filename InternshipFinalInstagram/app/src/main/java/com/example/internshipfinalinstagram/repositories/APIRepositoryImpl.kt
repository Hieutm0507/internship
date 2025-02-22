package com.example.internshipfinalinstagram.repositories

import android.util.Log
import com.example.internshipfinalinstagram.apis.ApiClient
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.AuthResponse
import com.example.internshipfinalinstagram.models.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepositoryImpl : APIRepository {
     override fun loginUser(loginRequest: LoginRequest, callback: (Result<AuthResponse>) -> Unit): Call<AuthResponse> {
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
                        }
                        else {
                            callback(Result.success(it))
                        }
                    }
                }
                else {
                    callback(Result.failure(Exception("Register failed with code: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<AuthResponse>?, t: Throwable) {
                callback(Result.failure(t))
            }
        })
        return ApiClient.getApi().registerUser(registerRequest)
    }
}