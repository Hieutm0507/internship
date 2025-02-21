package com.example.internshipfinalinstagram.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.models.AuthResponse
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl

class LoginViewModel(private val apiRepositoryImpl: APIRepositoryImpl) : ViewModel() {

    val authResult = MutableLiveData<Result<AuthResponse>>()
    val authState = MutableLiveData<Boolean>()
    private val authError = MutableLiveData<String>()

    fun login(loginRequest: LoginRequest) {
        authState.postValue(true) // Bắt đầu login

        apiRepositoryImpl.loginUser(loginRequest) { result ->
            authState.postValue(false) // Kết thúc login

            result.onSuccess { response ->
                authResult.postValue(Result.success(response))
            }.onFailure { error ->
                authError.postValue(error.message ?: "Unknown error")
                authResult.postValue(Result.failure(error))
            }
        }
    }
}