package com.example.internshipfinalinstagram

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipfinalinstagram.databinding.ActivitySelectImageBinding

class SelectImageActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySelectImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySelectImageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivClose.setOnClickListener {
            finish()
        }
    }
}