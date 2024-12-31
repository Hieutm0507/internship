package com.example.internshipweek6codeforslides.ex_listview

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.internshipweek6codeforslides.databinding.ActivityToDoListBinding

class ToDoListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityToDoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.main)

        val arrayAdapter: ArrayAdapter<*>
        val toDo = arrayOf(
            "Maths homework",
            "OOAD Report",
            "Crypto Assignment",
            "Graph Theory coding exercise",
            "Internship Task"
        )

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, toDo)
        binding.lvTodoList.adapter = arrayAdapter
    }
}