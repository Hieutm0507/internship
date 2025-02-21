package com.example.internshipfinalinstagram.models

data class AuthResponse (
    val status : Boolean,
    val data : UserData?,
    val code : Int,
    val message : String
)