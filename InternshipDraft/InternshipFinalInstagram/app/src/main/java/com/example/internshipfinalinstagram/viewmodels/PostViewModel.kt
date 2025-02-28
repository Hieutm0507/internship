package com.example.internshipfinalinstagram.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshipfinalinstagram.models.PostData
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import com.example.internshipfinalinstagram.untilities.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostViewModel(private val apiRepositoryImpl: APIRepositoryImpl) : ViewModel() {
    private lateinit var allPostList: List<PostData>
    private val _postsLiveData = MutableLiveData<PostDataState>()
    val postsLiveData: LiveData<PostDataState> get() = _postsLiveData

    fun getAllPosts(sort: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            emitPostDataState(isLoading = true)
            try {
                emitPostDataState(true)
                apiRepositoryImpl.getAllPosts(sort, page, perPage) {
                    val result = it?.data?.data?.let { it1 ->
                        allPostList = it1
                    }
                    if (result != null) {
                        emitPostDataState(result = allPostList)
                    }
                }
            } catch (e: Exception) {
                emitPostDataState(error = e.message.toString())
                Log.e("TAG_ERROR", "Error fetching posts: ${e.message}")
            }
        }
    }


    private fun emitPostDataState(
        isLoading: Boolean = false,
        result: List<PostData>? = null,
        error: String? = null
    ) {
        val dataState = PostDataState(isLoading, result, error)
        _postsLiveData.postValue(dataState) // Cập nhật LiveData
    }

    data class PostDataState(
        val isLoading: Boolean = false,
        val result: List<PostData>? = null,
        val error: String? = null
    )
}