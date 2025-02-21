package com.example.internshipfinalinstagram.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.LoginResponse
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(apiRepositoryImpl: APIRepositoryImpl) : ViewModel() {
    private val apiRepositoryImpl = APIRepositoryImpl()

    val loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginState = MutableLiveData<Boolean>()
    val loginError = MutableLiveData<String>()

    fun login(loginRequest: LoginRequest) {
        loginState.postValue(true)                          // Bắt đầu login, update status

        apiRepositoryImpl.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginState.postValue(false)                 // Ending login

                if (response.isSuccessful) {
                    response.body()?.let {
                        loginResult.postValue(Result.success(it))
                    } ?: loginResult.postValue(Result.failure(Exception("Empty response body")))
                } else {
                    val errorMessage = "Login failed with code: ${response.code()}"
                    loginError.postValue(errorMessage)
                    loginResult.postValue(Result.failure(Exception(errorMessage)))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginState.postValue(false)                 // End login
                loginError.postValue(t.message ?: "Unknown error")
                loginResult.postValue(Result.failure(t))
            }
        })
    }
}