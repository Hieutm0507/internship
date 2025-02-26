package com.example.internshipfinalinstagram.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internshipfinalinstagram.models.PostData
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import com.example.internshipfinalinstagram.untilities.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostViewModel(private val apiRepositoryImpl: APIRepositoryImpl) : ViewModel() {
    private val getPostState = MutableLiveData<PostDataState>()
    val get : LiveData<PostDataState> get() = getPostState

    fun getAllPosts(sort: String, page: Int, perPage: Int) {
        Coroutines.io {
            runCatching {
                emitPostDataState(isLoading = true)
                apiRepositoryImpl.getAllPosts(sort, page, perPage) {
                    val data = it.data.data
                    Log.d("TAG_POSTDATA", data.toString())
                }
            }.onFailure { error ->
                emitPostDataState(error = error.message.toString())
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
}

//    fun getAllPosts(sort : String, page : Int, perPage : Int) : List<PostData> {
//        var posts : List<PostData> = listOf()
//        apiRepositoryImpl.getAllPosts(sort, page, perPage) { response ->
//            response.onSuccess { data ->
//                allPosts.postValue(data)
//                postList.postValue(data.data.data) // Lưu danh sách PostData vào LiveData
//                posts = data.data.data
////                Log.d("TAG_POST_LIST", posts.toString())
//            }.onFailure { error ->
//                Log.e("TAG_POSTS", "API call failed: ${error.message}")
//            }
//        }
//        return posts
//    }
//}