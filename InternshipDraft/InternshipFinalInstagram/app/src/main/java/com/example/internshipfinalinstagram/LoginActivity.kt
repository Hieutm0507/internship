package com.example.internshipfinalinstagram

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipfinalinstagram.databinding.ActivityLoginBinding
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        observeViewModel()

        binding.btCreateAcc.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val loginRequest = LoginRequest(username, password)

            login(loginRequest)
        }
    }


    // TODO: Display the result of ViewModel after processing
    private fun observeViewModel() {
        loginViewModel.authResult.observe(this) { result ->
            result.onSuccess { response ->
                Toast.makeText(this, "Login Success: ${response.status}", Toast.LENGTH_SHORT).show()

                if (response.status) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()                // Để khi ấn nút Back sẽ not go back to LoginActivity này
                }
            }
            result.onFailure { error ->
                Toast.makeText(this, "Login Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // Displaying Progress Bar when connecting to the API
        loginViewModel.authState.observe(this) { isLoading ->
            if (isLoading) {
                binding.pbProgressBar.visibility = View.VISIBLE
            } else {
                binding.pbProgressBar.visibility = View.GONE
            }
        }
    }

    private fun login(loginRequest: LoginRequest) {
        loginViewModel.login(loginRequest)
    }
}