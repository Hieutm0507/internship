package com.example.internshipweek6recycleview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipweek6recycleview.databinding.ActivityInfoBinding
import com.example.internshipweek6recycleview.model.NhanVien

@Suppress("DEPRECATION")
class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.main)

        // Receive Information
        val receivedIntent = intent.getParcelableExtra<NhanVien>("EXTRA_SEND_EMPLOYEE")
        binding.tvIn4Name.text = receivedIntent?.name
        binding.tvIn4Username.text = receivedIntent?.id
        binding.tvIn4Department.text = receivedIntent?.department
        binding.tvIn4State.text = receivedIntent?.state

        // Activate BACK
        binding.btBack.setOnClickListener {
            finish()
        }
    }
}