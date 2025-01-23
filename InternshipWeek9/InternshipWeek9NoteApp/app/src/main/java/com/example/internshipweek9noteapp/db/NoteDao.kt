package com.example.internshipweek9noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.internshipweek9noteapp.model.Note

@Dao
interface NoteDao {
    // TODO: Declare methods to access database

    @Query("SELECT * FROM notes ORDER BY modifyTime DESC")
    fun getAllNote() : MutableList<Note>

    @Query("SELECT * FROM notes ORDER BY modifyTime ASC")
    fun getAllNoteASC() : MutableList<Note>

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNoteCreDESC() : MutableList<Note>

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNoteCreASC() : MutableList<Note>

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getAllNoteAZ() : MutableList<Note>

    @Query("SELECT * FROM notes ORDER BY title DESC")
    fun getAllNoteZA() : MutableList<Note>

    @Update
    fun updateNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)        // @Query("INSERT INTO Note")
    fun addNote(note: Note)

    @Delete                                                 // @Query("DELETE FROM notes WHERE id = :id")
    fun deleteNote(note: Note)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun searchNote(query: String?) : MutableList<Note>
}