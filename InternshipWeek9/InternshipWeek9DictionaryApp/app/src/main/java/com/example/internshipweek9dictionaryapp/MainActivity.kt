package com.example.internshipweek9dictionaryapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshipweek9dictionaryapp.adapter.ChineseAdapter
import com.example.internshipweek9dictionaryapp.adapter.VietChinaAdapter
import com.example.internshipweek9dictionaryapp.databinding.ActivityMainBinding
import com.example.internshipweek9dictionaryapp.db.DictDB
import com.example.internshipweek9dictionaryapp.model.Chinese
import com.example.internshipweek9dictionaryapp.model.VietChina
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var dictType: String = "Viet-Chi"
    private lateinit var chineseAdapter: ChineseAdapter
    private lateinit var vietChinaAdapter: VietChinaAdapter
    private lateinit var listChinese: List<Chinese>
    private lateinit var listVietTrung: List<VietChina>
    private lateinit var searchView: SearchView
    private var searchListChinese: List<Chinese> = listOf()
    private var searchListVietChinese: List<VietChina> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // TODO: Choose type of dictionary
        chooseDictType()


        // TODO: Get data for the lists
        val db = DictDB.getInstance(this)

        lifecycleScope.launch(Dispatchers.IO) {
            listVietTrung = db.getVietChinaDao().getAllWord()
            listChinese = db.getChineseDao().getAllWord()

            withContext(Dispatchers.Main) {
                setUpAdapter()
            }
        }


        // TODO: Searching
        searchView = binding.svSearch
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    displayOriginalList()
                } else {
                    displaySearchList(query)
                }
                return true
            }
        })
    }

    private fun chooseDictType() {
        binding.tvChiVie.setOnClickListener {
            dictType = "Viet-Chi"
            binding.tvChiVie.setTextColor(Color.parseColor("#FF000000"))
            binding.tvChinese.setTextColor(Color.parseColor("#808080"))
            setUpAdapter()

            binding.svSearch.setQuery("", false)
            binding.svSearch.clearFocus()
        }

        binding.tvChinese.setOnClickListener {
            dictType = "Chinese"
            binding.tvChinese.setTextColor(Color.parseColor("#FF000000"))
            binding.tvChiVie.setTextColor(Color.parseColor("#808080"))
            setUpAdapter()

            binding.svSearch.setQuery("", false)
            binding.svSearch.clearFocus()
        }
    }

    private fun setUpAdapter() {
        if (dictType == "Viet-Chi") {
            vietChinaAdapter = VietChinaAdapter(listVietTrung)
            binding.rvWords.layoutManager = LinearLayoutManager(this)
            binding.rvWords.adapter = vietChinaAdapter
        } else {
            chineseAdapter = ChineseAdapter(listChinese)
            binding.rvWords.layoutManager = LinearLayoutManager(this)
            binding.rvWords.adapter = chineseAdapter
        }
    }

    private fun displayOriginalList() {
        if (dictType == "Viet-Chi") {
            vietChinaAdapter.updateData(listVietTrung)
        } else {
            chineseAdapter.updateData(listChinese)
        }
    }

    private fun displaySearchList(query: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (dictType == "Viet-Chi") {
                searchListVietChinese = DictDB.getInstance(this@MainActivity).getVietChinaDao().searchWord(query)
                withContext(Dispatchers.Main) {
                    vietChinaAdapter.updateData(searchListVietChinese)
                }
            } else {
                searchListChinese = DictDB.getInstance(this@MainActivity).getChineseDao().searchWord(query)
                withContext(Dispatchers.Main) {
                    chineseAdapter.updateData(searchListChinese)
                }
            }
        }
    }
}