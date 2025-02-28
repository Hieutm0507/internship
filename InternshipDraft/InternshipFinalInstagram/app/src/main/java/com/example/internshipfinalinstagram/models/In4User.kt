package com.example.internshipfinalinstagram.models

data class In4User (
    val username : String,
    val name : String,
    val avatar : String = "",
    val gender : String = "",
    val address : String = "",
    val introduce : String = "",
    val totalPost : Int
)