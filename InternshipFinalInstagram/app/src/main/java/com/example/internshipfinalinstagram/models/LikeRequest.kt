package com.example.internshipfinalinstagram.models

data class LikeRequest (
    val userId : String,
    val postId : String,
    val likeValue : Int
)