package com.example.internshipfinalinstagram

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipfinalinstagram.databinding.ActivityRegisterBinding
import com.example.internshipfinalinstagram.models.RegisterRequest

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btBack.setOnClickListener {
            finish()
        }

        binding.btSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
        }
    }

    private fun register(registerRequest: RegisterRequest) {

    }
}