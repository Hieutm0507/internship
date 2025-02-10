package com.example.internshipweek9dictionaryapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "han_tu")
@Parcelize
data class Chinese (
    @PrimaryKey(autoGenerate = true) @ColumnInfo("_id")
    val id : Int?,

    @ColumnInfo("han")
    val han : String?,

    @ColumnInfo("viet")
    val viet : String?,

    @ColumnInfo("pinyin")
    val pinyin : String?,

    @ColumnInfo("mean")
    val mean : String?
) : Parcelable