package com.example.internshipweek6recycleview.model

data class NhanVien(
    var id: String,
    var name: String,
    var department: String,
    var state: String,
    var isSelect: Boolean = false)