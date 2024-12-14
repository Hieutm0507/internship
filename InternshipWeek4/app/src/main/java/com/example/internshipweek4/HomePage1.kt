package com.example.internshipweek4

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ll_home_page_1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "Activity is started")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Activity is resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "Activity is paused")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Activity is terminated")
    }
}