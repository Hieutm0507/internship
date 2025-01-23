package com.example.internshipweek9noteapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,            // UNIQUE and UNCHANGEABLE

    // Sử dụng @ColumnInfo(name = "title") để đặt tên cột
    // Không sử dụng thì sẽ mặc định đặt tên theo tên biến
    var title: String = "",

    var content: String = "",

    var createTime: Long = 0,

    var modifyTime: Long = 0
) : Parcelable