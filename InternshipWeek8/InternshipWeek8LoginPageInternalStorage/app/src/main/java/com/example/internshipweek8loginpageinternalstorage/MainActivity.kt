package com.example.internshipweek8loginpageinternalstorage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.internshipweek8loginpageinternalstorage.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var saveEmail : String? = ""
    private var savePasswd : String? = ""
    private var rememberMe: Boolean? = null
    private lateinit var registerAcc: SharedPreferences
    private var listAcc : MutableList<User> = mutableListOf()
    private var fileName: String = "internalStorage.txt"
    private var filepath: String = "saveInput"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.main)

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // TODO: Save data when Activity's State is onPause()
    override fun onPause() {
        super.onPause()
        saveData()
    }

    // TODO: Call back Data when Activity's State is onResume()
    override fun onResume() {
        super.onResume()
        callData()
    }


    @SuppressLint("CommitPrefEdits")
    fun saveData() {
        saveEmail = binding.etEmail.text.toString()
        savePasswd = binding.etPassword.text.toString()
        rememberMe = binding.cbRememberMe.isChecked

        val data = openFileOutput(fileName, MODE_PRIVATE)
        val outputStreamWriter = OutputStreamWriter(data)
        outputStreamWriter.write("${saveEmail}__${savePasswd}__${rememberMe}")
        outputStreamWriter.close()
        Toast.makeText(this, "Information saved", LENGTH_SHORT).show()
    }

    private fun callData() {
        val inputStream : FileInputStream = openFileInput(fileName)
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)

        // Read file
        // TODO: Đọc từng dòng
        val stringBuilder = StringBuilder()
        var line: String?               // Xử lý có thể NULL, không thì vòng lặp dòng dưới lỗi, dẫn đến crash
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }

        // Close Streams    (NOTE: Close theo thứ tự)
        bufferedReader.close()
        inputStreamReader.close()
        inputStream.close()

        val savedData = stringBuilder.toString().split("__")
        Log.d("TAG_DATA", savedData.toString())
        val savedEmail = savedData[0]
        val savedPasswd = savedData[1]
        val rememberMe = savedData[2].toBoolean()

        binding.etEmail.setText(savedEmail)
        binding.etPassword.setText(savedPasswd)
        binding.cbRememberMe.isChecked = rememberMe
    }
}