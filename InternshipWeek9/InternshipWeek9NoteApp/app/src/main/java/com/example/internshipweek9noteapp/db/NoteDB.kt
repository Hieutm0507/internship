package com.example.internshipweek9noteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.internshipweek9noteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase() {
    companion object {                      // Chứa thuộc tính tĩnh (NAME)
        const val NAME = "Note_DB"
        @Volatile
        private var instance: NoteDB? = null
        fun getInstance(context: Context): NoteDB {
            return instance ?: synchronized(this) {
                val instanceCreate =
                    Room.databaseBuilder(context.applicationContext, NoteDB::class.java, NAME)
                        .allowMainThreadQueries().build()
                instance = instanceCreate
                instance!!
            }
        }
    }

    abstract fun getNoteDao() : NoteDao
}