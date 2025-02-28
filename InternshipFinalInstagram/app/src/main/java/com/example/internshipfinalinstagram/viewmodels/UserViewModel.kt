package com.example.internshipfinalinstagram.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshipfinalinstagram.models.In4UserResponse
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import com.example.internshipfinalinstagram.viewmodels.PostViewModel.PostDataState
import kotlinx.coroutines.launch

class UserViewModel(private val apiRepositoryImpl: APIRepositoryImpl) : ViewModel() {
    private var in4Response: In4UserResponse? = null
    private val getIn4State = MutableLiveData<GetIn4UserState>()
    val in4LiveData : LiveData<GetIn4UserState> get() = getIn4State

    fun getUserIn4(username : String) {
        viewModelScope.launch {
            emitIn4UserState(true)
            try {
                emitIn4UserState(true)
                apiRepositoryImpl.getUserIn4(username) {
                    in4Response = it
                    emitIn4UserState(result = in4Response)
                }
            }
            catch (e : Exception) {
                emitIn4UserState(error = e.message.toString())
            }
        }
    }

    private fun emitIn4UserState(
        isLoading: Boolean = false,
        result: In4UserResponse? = null,
        error: String? = null
    ) {
        val dataState = GetIn4UserState(isLoading, result, error)
        getIn4State.postValue(dataState) // Cập nhật LiveData
    }

    data class GetIn4UserState(
        val isLoading: Boolean = false,
        val result: In4UserResponse? = null,
        val error: String? = null
    )
}