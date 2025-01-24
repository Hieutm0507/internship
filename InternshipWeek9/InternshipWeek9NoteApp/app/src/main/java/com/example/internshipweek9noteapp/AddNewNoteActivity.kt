package com.example.internshipweek9noteapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipweek9noteapp.databinding.ActivityAddNewNoteBinding
import com.example.internshipweek9noteapp.db.NoteDB
import com.example.internshipweek9noteapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNewNoteActivity : AppCompatActivity() {
    private lateinit var bindingAdd: ActivityAddNewNoteBinding
    private var newTitle : String = ""
    private var newContent : String = ""
    private var newModifyTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingAdd = ActivityAddNewNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindingAdd.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        newModifyTime = System.currentTimeMillis()
        bindingAdd.tvModifyTime.text = convertTime(newModifyTime)
        Log.d("TAG_NEW", bindingAdd.tvModifyTime.text.toString())

        bindingAdd.ibDone.setOnClickListener {
            getData()
            if (newTitle.isNotEmpty() || newContent.isNotEmpty()) {
                createNewNote()
            }
        }

        bindingAdd.ibBack.setOnClickListener {
            getData()
            if (bindingAdd.etTitle.text.toString() == newTitle && bindingAdd.etNote.text.toString() == newContent) {
                finish()
            }
            else {
                goBack()
            }
        }
    }

    private fun getData() {
        newTitle = bindingAdd.etTitle.text.toString()
        newContent = bindingAdd.etNote.text.toString()
    }

    private fun goBack() {
        if (newTitle.isNotEmpty() || newContent.isNotEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Save changes")
                .setIcon(R.drawable.ic_warning)
                .setMessage("Save your changes first?")
                .setPositiveButton("Yes") { dialog, _ ->
                    createNewNote()
                    finish()
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog,_ ->
                    finish()
                    dialog.dismiss()
                }
                .create().show()
        }
        else {
            finish()
        }
    }

    private fun createNewNote() {
        val newNote = Note(title = newTitle, content = newContent, modifyTime = newModifyTime)
        NoteDB.getInstance(this).getNoteDao().addNote(newNote)
        Toast.makeText(this, "Add new note successfully", Toast.LENGTH_SHORT).show()
    }

    private fun convertTime (time : Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd   HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(Date(time))

        return formattedDate
    }
}