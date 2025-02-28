package com.example.internshipfinalinstagram.models

data class AddPostResponse (
    val status : Boolean,
    val data : PostData,
    val code : Int,
    val message : String
)