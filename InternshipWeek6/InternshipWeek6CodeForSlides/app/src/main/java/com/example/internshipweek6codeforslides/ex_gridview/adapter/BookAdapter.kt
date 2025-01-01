package com.example.internshipweek6codeforslides.ex_gridview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.internshipweek6codeforslides.R
import com.example.internshipweek6codeforslides.ex_gridview.model.Book

class BookAdapter (
    private val bookList: List<Book>,
    private val context: Context
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var tvName: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var ivBook: ImageView


    override fun getCount(): Int {
        return bookList.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (view == null) {
            view = layoutInflater!!.inflate(R.layout.item_gridview, null)
        }

        tvName = view!!.findViewById(R.id.tv_book_name)
        tvAuthor = view.findViewById(R.id.tv_author)
        ivBook = view.findViewById(R.id.iv_book_cover)

        tvName.text = bookList[position].name
        tvAuthor.text = bookList[position].author
        ivBook.setImageResource(bookList[position].image)

        return view
    }

}