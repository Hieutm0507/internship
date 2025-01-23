package com.example.internshipweek9noteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipweek9noteapp.databinding.ActivityDisplayNoteBinding
import com.example.internshipweek9noteapp.db.NoteDB
import com.example.internshipweek9noteapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DEPRECATION")
class DisplayNoteActivity : AppCompatActivity() {
    private lateinit var bindingNote : ActivityDisplayNoteBinding
    private lateinit var receivedIntent : Note
    private var selectID : Int = 0
    private var selectTitle : String = ""
    private var selectContent : String = ""
    private var selectModifyTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingNote = ActivityDisplayNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindingNote.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: DISPLAY THE CONTENT
        receivedIntent = intent.getParcelableExtra<Note>("EXTRA_SEND_NOTE")!!
        selectID = receivedIntent.id
        selectTitle = receivedIntent.title
        selectContent = receivedIntent.content
        selectModifyTime = receivedIntent.modifyTime
        bindingNote.etTitle.setText(selectTitle)
        bindingNote.etNote.setText(selectContent)
        bindingNote.tvModifyTime.text = convertTime(selectModifyTime)

        bindingNote.ibDone.setOnClickListener {
            saveChanges()
        }

        bindingNote.ibBack.setOnClickListener {
            if (bindingNote.etTitle.text.toString() == selectTitle && bindingNote.etNote.text.toString() == selectContent) {
                finish()
            }
            else {
                AlertDialog.Builder(this)
                    .setTitle("Save changes")
                    .setIcon(R.drawable.ic_warning)
                    .setMessage("Save your changes first?")
                    .setPositiveButton("Yes") { dialog, _ ->
                        saveChanges()
                        finish()
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog,_ ->
                        finish()
                        dialog.dismiss()
                    }
                    .create().show()
            }
        }
    }

    private fun saveChanges() {
        selectTitle = bindingNote.etTitle.text.toString()
        selectContent = bindingNote.etNote.text.toString()
        selectModifyTime = System.currentTimeMillis()

        receivedIntent = Note(id = selectID, title = bindingNote.etTitle.text.toString(), content = bindingNote.etNote.text.toString(), modifyTime = selectModifyTime)
        Log.d("TAG_SAVE", receivedIntent.toString())
        NoteDB.getInstance(this).getNoteDao().updateNote(receivedIntent)
        NoteDB.getInstance(this).getNoteDao().getAllNote()

        Toast.makeText(applicationContext, "Save changes successfully", Toast.LENGTH_SHORT).show()
    }

    private fun convertTime (time : Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd   HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(Date(time))

        return formattedDate
    }
}