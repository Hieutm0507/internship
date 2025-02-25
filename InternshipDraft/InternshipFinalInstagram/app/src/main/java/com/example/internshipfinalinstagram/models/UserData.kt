package com.example.internshipfinalinstagram.models

import com.google.gson.annotations.SerializedName

// Kiến thức cũ: "?" để khai báo biến có thể NULL

data class UserData (
    @SerializedName("_id")
    val id : String?,
    val username : String,
    val password : String?,
    val name : String,
    val gender : String?,
    val avatar : String?,
    val address : String?,
    val introduce : String?
)