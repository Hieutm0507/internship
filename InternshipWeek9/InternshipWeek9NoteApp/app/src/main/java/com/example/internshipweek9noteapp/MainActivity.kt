package com.example.internshipweek9noteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshipweek9noteapp.adapter.NoteAdapter
import com.example.internshipweek9noteapp.databinding.ActivityMainBinding
import com.example.internshipweek9noteapp.db.NoteDB
import com.example.internshipweek9noteapp.model.Note

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter
    private var isShown : Boolean = false
    private lateinit var listNote : MutableList<Note>


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Khởi tạo database
        listNote = NoteDB.getInstance(this).getNoteDao().getAllNote().toMutableList()

        // Set up Adapter
        noteAdapter = NoteAdapter()
        noteAdapter.setData(listNote)

        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvListNote.layoutManager = linearLayoutManager
        binding.rvListNote.adapter = noteAdapter

        // Load Data when this Activity is created
        loadData()

        // Event Listener for Adapter
        noteAdapter.setOnClickListener(object : NoteAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val note: Note = listNote[position]

                val intent = Intent(this@MainActivity, DisplayNoteActivity::class.java)
                intent.putExtra("EXTRA_SEND_NOTE", note)
                startActivity(intent)
            }

            override fun deleteItem(item: Note, view: View) {
                deleteNote(item, view)
            }
        })


        // TODO: Display Search Bar
        binding.ivSearch.setOnClickListener {
            if (!isShown) {
                binding.svSearchBar.visibility = View.VISIBLE
                isShown = true
            }
            else {
                binding.svSearchBar.visibility = View.GONE
                isShown = false
            }
        }

        // TODO: Search by title and content

        // TODO: Filter the list

        // TODO: Add new note
        binding.abAddNote.setOnClickListener {
            moveToDisplayActivity()


            // Tạm thời
//            val noteNew = Note(title = "12345", content = "This is content")
//            NoteDB.getInstance(this).getNoteDao().addNote(noteNew)
//            loadData()
        }
    }

    private fun moveToDisplayActivity() {
        val intent = Intent(this, DisplayNoteActivity::class.java)
        startActivity(intent)
    }

    private fun deleteNote(item: Note, view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.item_more)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_note -> {
                    AlertDialog.Builder(this)
                        .setTitle("Delete Note")
                        .setIcon(R.drawable.ic_warning)
                        .setMessage("Are you sure to delete this note?")
                        .setPositiveButton("Yes") { dialog, _ ->
                            //Delete note
                            NoteDB.getInstance(this).getNoteDao().deleteNote(item)
                            loadData()
                            Toast.makeText(applicationContext, "Delete successfully", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        .setNegativeButton("No") { dialog,_ ->
                            dialog.dismiss()
                        }
                        .create().show()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun loadData() {
        listNote = NoteDB.getInstance(this).getNoteDao().getAllNote().toMutableList()
        noteAdapter.setData(listNote)
    }

}