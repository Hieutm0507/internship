package com.example.internshipweek8loginpageinternalstorage

data class User (
    var email : String,             // UNIQUE
    var passwd : String,
    val username : String,          // UNIQUE
    val key : String
)