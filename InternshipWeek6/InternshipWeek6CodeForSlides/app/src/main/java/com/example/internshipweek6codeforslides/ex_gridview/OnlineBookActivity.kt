package com.example.internshipweek6codeforslides.ex_gridview

import android.os.Bundle
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipweek6codeforslides.R
import com.example.internshipweek6codeforslides.databinding.ActivityOnlineBookBinding
import com.example.internshipweek6codeforslides.ex_gridview.adapter.BookAdapter
import com.example.internshipweek6codeforslides.ex_gridview.model.Book

class OnlineBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnlineBookBinding
    lateinit var gvBook: GridView
    lateinit var bookList: MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gvBook = binding.gvOnlineBooks
        bookList.add(Book("Calulus", "unknown", R.drawable.img_calculus))


//        bookList = bookList + Book("Calulus", "unknown", R.drawable.img_calculus)
//        bookList = bookList + Book("Linear Algebra", "unknown", R.drawable.img_linear)
//        bookList = bookList + Book("Probability and Statistic", "unknown", R.drawable.img_prob)
//        bookList = bookList + Book("Algebraic Structure", "unknown", R.drawable.img_algebraic_structure)

        val bookAdapter = BookAdapter(bookList, this@OnlineBookActivity)
        gvBook.adapter = bookAdapter
    }
}