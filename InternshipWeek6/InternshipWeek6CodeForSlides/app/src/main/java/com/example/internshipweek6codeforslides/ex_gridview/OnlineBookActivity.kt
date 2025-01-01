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
    var bookList: MutableList<Book> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gvBook = binding.gvOnlineBooks
        bookList.add(Book("Calulus", "Morris Kline", R.drawable.img_calculus))
        bookList.add(Book("Linear Algebra", "Stephen H. Friedberg", R.drawable.img_linear))
        bookList.add(Book("Probability and Statistic", "Alder/Roessler", R.drawable.img_prob))
        bookList.add(Book("Algebraic Structure", "Larry J. Gerstein", R.drawable.img_algebraic_structure))

        val bookAdapter = BookAdapter(bookList, this@OnlineBookActivity)
        gvBook.adapter = bookAdapter
    }
}