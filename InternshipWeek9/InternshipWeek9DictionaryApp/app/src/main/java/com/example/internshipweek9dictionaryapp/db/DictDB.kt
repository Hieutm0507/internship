package com.example.internshipweek9dictionaryapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.internshipweek9dictionaryapp.model.Chinese
import com.example.internshipweek9dictionaryapp.model.VietChina

@Database(entities = [VietChina::class, Chinese::class], version = 1, exportSchema = false)
abstract class DictDB : RoomDatabase() {
    companion object {
        private const val NAME = "Dict_DB"
        @Volatile
        private var instance : DictDB? = null
        fun getInstance(context: Context) : DictDB {
            return instance ?: synchronized(this) {
                instance =
                    Room.databaseBuilder(context.applicationContext, DictDB::class.java, NAME)
                        .createFromAsset("vt.db")
                        .allowMainThreadQueries().build()
                instance!!
            }
        }
    }

    abstract fun getVietChinaDao() : VietChinaDao

    abstract fun getChineseDao() : ChineseDao
}