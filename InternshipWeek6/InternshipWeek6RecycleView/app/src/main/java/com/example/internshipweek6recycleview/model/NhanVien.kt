package com.example.internshipweek6recycleview.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NhanVien(
    var id: String,
    var name: String,
    var department: String,
    var state: String,
    var isSelect: Boolean = false) : Parcelable