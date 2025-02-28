package com.example.internshipfinalinstagram.models

import com.google.gson.annotations.SerializedName

data class PostData (
    @SerializedName("_id")
    val id : String,

    val author : UserData,
    val images : List<String>,
        // Use List instead of Array in order not to override fun equals() and hashCode()

    val content : String = "",
    val createdAt : String,
    val updatedAt : String,
    val listLike : List<UserData> = emptyList(),
    val totalLike : Int? = 0
)