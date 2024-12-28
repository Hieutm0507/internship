package com.example.internshipweek6recycleview

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipweek6recycleview.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.main)

        // Receive Information
        binding.tvIn4Name.text = intent.getStringExtra("EXTRA_SEND_NAME")
        binding.tvIn4Username.text = intent.getStringExtra("EXTRA_SEND_USERNAME")
        binding.tvIn4Department.text = intent.getStringExtra("EXTRA_SEND_DEPARTMENT")
        binding.tvIn4State.text = intent.getStringExtra("EXTRA_SEND_STATE")

        // Activate BACK
        binding.btBack.setOnClickListener {
            finish()
        }
    }
}