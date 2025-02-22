package com.example.internshipfinalinstagram

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipfinalinstagram.databinding.ActivityRegisterBinding
import com.example.internshipfinalinstagram.models.RegisterRequest
import com.example.internshipfinalinstagram.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private val registerViewModel : AuthViewModel by viewModel()

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

        observeViewModel()

        binding.btBack.setOnClickListener {
            finish()
        }

        binding.btSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val registerRequest = RegisterRequest(name, username, password)

            register(registerRequest)
        }
    }

    private fun observeViewModel() {
        registerViewModel.authResult.observe(this) { result ->
            result.onSuccess { response ->
                Toast.makeText(this, "Register Success: ${response.status}", Toast.LENGTH_SHORT).show()

                if (response.status) {
                    finish()
                }
            }
            result.onFailure { error ->
                Toast.makeText(this, "Register Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // Displaying Progress Bar when connecting to the API
        registerViewModel.authState.observe(this) { isLoading ->
            if (isLoading) {
                binding.pbProgressBar.visibility = View.VISIBLE
            } else {
                binding.pbProgressBar.visibility = View.GONE
            }
        }
    }

    private fun register(registerRequest: RegisterRequest) {
        registerViewModel.register(registerRequest)
    }
}