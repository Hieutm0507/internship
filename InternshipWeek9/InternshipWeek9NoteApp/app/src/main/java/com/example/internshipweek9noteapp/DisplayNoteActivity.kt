package com.example.internshipweek9noteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipweek9noteapp.databinding.ActivityDisplayNoteBinding
import com.example.internshipweek9noteapp.db.NoteDB
import com.example.internshipweek9noteapp.model.Note

@Suppress("DEPRECATION")
class DisplayNoteActivity : AppCompatActivity() {
    private lateinit var bindingNote : ActivityDisplayNoteBinding
    private lateinit var receivedIntent : Note

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
        val noteTitle = receivedIntent.title
        val noteContent = receivedIntent.content
        bindingNote.etTitle.setText(noteTitle)
        bindingNote.etNote.setText(noteContent)

        bindingNote.ibDone.setOnClickListener {
            saveChanges()
        }

        bindingNote.ibBack.setOnClickListener {
            if (bindingNote.etTitle.text.toString() == noteTitle && bindingNote.etNote.text.toString() == noteContent) {
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
        receivedIntent = Note(title = bindingNote.etTitle.text.toString(), content = bindingNote.etNote.text.toString())
        NoteDB.getInstance(this).getNoteDao().updateNote(receivedIntent)
        NoteDB.getInstance(this).getNoteDao().getAllNote()

        Toast.makeText(applicationContext, "Save changes successfully", Toast.LENGTH_SHORT).show()


        val intentResult = Intent()
        setResult(Activity.RESULT_OK, intentResult)
    }

    interface SaveChanges {

    }
}