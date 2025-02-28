package com.example.internshipfinalinstagram.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshipfinalinstagram.models.PostData
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import kotlinx.coroutines.launch
import java.io.File

class PostViewModel(private val apiRepositoryImpl: APIRepositoryImpl) : ViewModel() {
    private lateinit var allPostList: List<PostData>
    private val getPostState = MutableLiveData<PostDataState>()
    val postsLiveData : LiveData<PostDataState> get() = getPostState
    val addPostState = MutableLiveData<AddPostState>()

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

    fun getPostsOfUser(username : String, sort: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            emitPostDataState(isLoading = true)
            try {
                emitPostDataState(true)
                apiRepositoryImpl.getPostsOfUser(username, sort, page, perPage) {
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
        getPostState.postValue(dataState) // Cập nhật LiveData
    }

    data class PostDataState(
        val isLoading: Boolean = false,
        val result: List<PostData>? = null,
        val error: String? = null
    )

    fun addPost(userId: String, imageFiles: List<File>, content: String) {
        viewModelScope.launch {
            emitPostDataState(isLoading = true)
            try {
                emitPostDataState(true)
                apiRepositoryImpl.addPost(userId, imageFiles, content) {
                    val result = it?.status
                    if (result != null) {
                        emitAddPostState(result = result)
                    }
                }
            } catch (e: Exception) {
                emitPostDataState(error = e.message.toString())
                Log.e("TAG_ERROR", "Error adding posts: ${e.message}")
            }
        }
    }

    private fun emitAddPostState(
        isLoading: Boolean = false,
        result: Boolean = false,
        error: String? = null
    ) {
        val dataState = AddPostState(isLoading, result, error)
        addPostState.postValue(dataState) // Cập nhật LiveData
    }

    data class AddPostState(
        val isLoading: Boolean = false,
        val result: Boolean = false,
        val error: String? = null
    )
}
