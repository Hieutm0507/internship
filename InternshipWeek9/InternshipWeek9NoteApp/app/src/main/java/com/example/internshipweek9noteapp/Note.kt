package com.example.internshipweek9noteapp

data class Note (
    val id: Int = 0,            // UNIQUE and UNCHANGEABLE
    var title: String = "",
    var content: String = "",
    var editTime: Long = 0
)