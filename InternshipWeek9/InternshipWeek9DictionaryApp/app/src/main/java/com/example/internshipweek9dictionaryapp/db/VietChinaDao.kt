package com.example.internshipweek9dictionaryapp.db

import androidx.room.Dao
import androidx.room.Query
import com.example.internshipweek9dictionaryapp.model.VietChina

@Dao
interface VietChinaDao {
    @Query("SELECT * FROM viet_trung ORDER BY _id ASC")
    fun getAllWord() : List<VietChina>

    @Query("SELECT * FROM viet_trung WHERE search LIKE '%' || :query || '%'")
    fun searchWord(query: String?) : List<VietChina>
}