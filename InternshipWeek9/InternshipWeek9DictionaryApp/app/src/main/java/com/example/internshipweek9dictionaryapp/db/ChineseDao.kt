package com.example.internshipweek9dictionaryapp.db

import androidx.room.Dao
import androidx.room.Query
import com.example.internshipweek9dictionaryapp.model.Chinese

@Dao
interface ChineseDao {
    @Query("SELECT * FROM han_tu ORDER BY _id ASC")
    fun getAllWord() : List<Chinese>

    @Query("SELECT * FROM han_tu WHERE viet LIKE '%' || :query || '%'")
    fun searchWord(query: String?) : List<Chinese>
}