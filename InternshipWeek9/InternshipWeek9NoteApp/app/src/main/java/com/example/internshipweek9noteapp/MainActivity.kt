package com.example.internshipweek9noteapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshipweek9noteapp.adapter.NoteAdapter
import com.example.internshipweek9noteapp.databinding.ActivityMainBinding
import com.example.internshipweek9noteapp.db.NoteDB
import com.example.internshipweek9noteapp.model.Note
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var listNote : MutableList<Note>
    private lateinit var listNoteASC : MutableList<Note>
    private lateinit var listNoteCreTimeNewToOld : MutableList<Note>
    private lateinit var listNoteCreTimeOldToNew : MutableList<Note>
    private lateinit var listNoteAZ : MutableList<Note>
    private lateinit var listNoteZA : MutableList<Note>
    private lateinit var searchView : SearchView
    private lateinit var searchListNote : MutableList<Note>
    private var isShown : Boolean = false               // For displaying SearchView
    private var optionSelect : Int = 0                  // For Sorting

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

        // Event Listener for Adapter
        noteAdapter.setOnClickListener(object : NoteAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val note: Note = listNote[position]

                val intent = Intent(this@MainActivity, DisplayNoteActivity::class.java)
                intent.putExtra("EXTRA_SEND_NOTE", note)
                Log.d("TAG_CHOSEN", note.toString())
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
        searchView = binding.svSearchBar
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    searchListNote = NoteDB.getInstance(this@MainActivity).getNoteDao().searchNote(it)
                    noteAdapter.setData(searchListNote)
                }
                if (query.isNullOrEmpty()) {
                    noteAdapter.setData(listNote)
                }
                return true
            }
        })

        // TODO: Filter the list
        binding.ivSort.setOnClickListener {
            sortingNotes()
        }

        // TODO: Add new note
        binding.abAddNote.setOnClickListener {
            val intentAdd = Intent(this, AddNewNoteActivity::class.java)
            startActivity(intentAdd)


            // Tạm thời
//            val noteNew = Note(title = "Chạy deadline", content = "Deadline dí nhưng vẫn chill")
//            NoteDB.getInstance(this).getNoteDao().addNote(noteNew)
//            loadData()
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
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

    private fun sortingNotes() {
        val items = arrayOf(
            "Modification Time (Newest first)",
            "Modification Time (Oldest first)",
            "Creation Time (Newest First)",
            "Creation Time (Oldest First)",
            "Name A-Z",
            "Name Z-A"
        )
        MaterialAlertDialogBuilder(this)
            .setTitle("Sorted by").setSingleChoiceItems(items, optionSelect) { _,selectedOptionIndex ->
                optionSelect = selectedOptionIndex
            }
            .setPositiveButton("OK"){ _,_ ->
                when(optionSelect) {
                    0 -> {
                        optionSelect = 0
                        noteAdapter.setData(listNote)
                        Toast.makeText(this, "Sorted by Modification Time NTO", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        optionSelect = 1
                        listNoteASC = NoteDB.getInstance(this).getNoteDao().getAllNoteASC()
                        noteAdapter.setData(listNoteASC)
                        Toast.makeText(this, "Sorted by Modification Time OTN", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        optionSelect = 2
                        listNoteCreTimeNewToOld = NoteDB.getInstance(this).getNoteDao().getAllNoteCreDESC()
                        noteAdapter.setData(listNoteCreTimeNewToOld)
                        Toast.makeText(this, "Sorted by Creation Time NTO", Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        optionSelect = 3
                        listNoteCreTimeOldToNew = NoteDB.getInstance(this).getNoteDao().getAllNoteCreASC()
                        noteAdapter.setData(listNoteCreTimeOldToNew)
                        Toast.makeText(this, "Sorted by Creation Time OTN", Toast.LENGTH_SHORT).show()
                    }
                    4 -> {
                        optionSelect = 4
                        listNoteAZ = NoteDB.getInstance(this).getNoteDao().getAllNoteAZ()
                        noteAdapter.setData(listNoteAZ)
                        Toast.makeText(this, "Sorted by Name A-Z", Toast.LENGTH_SHORT).show()
                    }
                    5 -> {
                        optionSelect = 5
                        listNoteZA = NoteDB.getInstance(this).getNoteDao().getAllNoteZA()
                        noteAdapter.setData(listNoteZA)
                        Toast.makeText(this, "Sorted by Name Z-A", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog,_ ->
                dialog.dismiss()
            }
            .show()
    }

}