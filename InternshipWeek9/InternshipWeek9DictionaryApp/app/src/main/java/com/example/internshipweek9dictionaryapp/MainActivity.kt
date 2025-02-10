package com.example.internshipweek9dictionaryapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshipweek9dictionaryapp.adapter.DictAdapter
import com.example.internshipweek9dictionaryapp.databinding.ActivityMainBinding
import com.example.internshipweek9dictionaryapp.db.DictDB
import com.example.internshipweek9dictionaryapp.model.Chinese
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var dictType : String
    private lateinit var chineseAdapter: DictAdapter
    private lateinit var listChinese: List<Chinese>

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

        chooseDictType()

        val db = DictDB.getInstance(this)
//        Log.d("TAG_DB", db.toString())

//        listChinese = db.getChineseDao().getAllWord()

        lifecycleScope.launch(Dispatchers.IO) {
            listChinese = db.getChineseDao().getAllWord()

            withContext(Dispatchers.Main) {
                chineseAdapter = DictAdapter(listChinese)
                binding.rvWords.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.rvWords.adapter = chineseAdapter
            }
//            listChinese.forEach { hanTu ->
//                Log.d("TAG_DB", "Item: $hanTu")
//            }

            val listVietTrung = db.getVietChinaDao().getAllWord()
//            listVietTrung.forEach { vietTrung ->
//                Log.d("TAG_DB_HAN_TU", vietTrung.toString())
//            }
        }


        // Setup Adapter
//        chineseAdapter = DictAdapter(listChinese)
    }

    private fun chooseDictType() {
        val listDict = listOf("Vietnamese - Chinese", "Han Character")
        val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, listDict)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnChooseLang.adapter = adapter

        binding.spnChooseLang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                dictType = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "Selected $dictType dictionary", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}