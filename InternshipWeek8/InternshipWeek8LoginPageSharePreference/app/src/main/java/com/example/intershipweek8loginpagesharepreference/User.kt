package com.example.intershipweek8loginpagesharepreference

data class User (
    var email : String,             // UNIQUE
    var passwd : String,
    val username : String,          // UNIQUE
    val key : String
)