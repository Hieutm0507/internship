package com.example.internshipweek9dictionaryapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipweek9dictionaryapp.databinding.ActivityMainBinding
import com.example.internshipweek9dictionaryapp.db.DictDB
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var dictType : String

    @OptIn(DelicateCoroutinesApi::class)
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
        Log.d("TAG_DB", db.toString())

        GlobalScope.launch(Dispatchers.IO) {
            val listHanTu = db.getChineseDao().getAllWord()
            listHanTu.forEach { hanTu ->
                Log.d("TAG_DB_HAN_TU", hanTu.toString())
            }

//            val listVietTrung = db.getVietChinaDao().getAllWord()
//            listVietTrung.forEach { vietTrung ->
//                Log.d("TAG_DB_HAN_TU", vietTrung.toString())
//            }
        }
    }

    private fun chooseDictType() {
        val listDict = listOf("Vietnamese - Chinese", "Han Character")
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, listDict)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnChooseLang.adapter = adapter

        binding.spnChooseLang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, vie: View?, position: Int, id: Long) {
                dictType = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "Selected $dictType dictionary", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}