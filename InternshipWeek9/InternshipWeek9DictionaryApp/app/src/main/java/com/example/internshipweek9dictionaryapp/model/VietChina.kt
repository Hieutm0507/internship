package com.example.internshipweek9dictionaryapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "viet_trung",
    indices = [Index(value = ["search"], name = "vt_s"),
        Index(value = ["word"], name = "vt_w")]
)
@Parcelize
data class VietChina(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("_id")
    val id: Int?,

    @ColumnInfo("word")
    val word: String?,

    @ColumnInfo("content")
    val content: String?,

    @ColumnInfo("search")
    val search: String?
) : Parcelable