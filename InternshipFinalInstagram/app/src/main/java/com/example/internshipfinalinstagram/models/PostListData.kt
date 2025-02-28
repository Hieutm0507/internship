package com.example.internshipfinalinstagram.models

data class PostListData (
    val page : Int = 1,     // Is the page number displayed currently
    val totalPage : Int,    // Is the number that takes totalPost divided to perPage
    val totalPost : Int,
    val data : List<PostData>
)