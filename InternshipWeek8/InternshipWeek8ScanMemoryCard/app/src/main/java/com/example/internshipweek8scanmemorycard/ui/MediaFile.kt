package com.example.internshipweek8scanmemorycard.ui

import android.net.Uri

data class MediaFile (
    val uri: Uri,
    val name: String,
    val type: MediaType
)