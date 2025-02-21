package com.example.internshipfinalinstagram

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.internshipfinalinstagram.databinding.ActivityLoginBinding
import com.example.internshipfinalinstagram.models.LoginRequest
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import com.example.internshipfinalinstagram.viewmodels.LoginViewModel

@Suppress("UNCHECKED_CAST")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

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

        val apiRepository = APIRepositoryImpl()
        loginViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(apiRepository) as T
            }
        })[LoginViewModel::class.java]

        observeViewModel()
    }

    private fun observeViewModel() {
        loginViewModel.loginResult.observe(this) { result ->
            result.onSuccess { response ->
                Toast.makeText(this, "Login Success: ${response.status}", Toast.LENGTH_SHORT).show()

                if (response.status) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            result.onFailure { error ->
                Toast.makeText(this, "Login Failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.loginState.observe(this) { isLoading ->
            if (isLoading) {
                binding.pbProgressBar.visibility = View.VISIBLE
            } else {
                binding.pbProgressBar.visibility = View.GONE
            }
        }

        loginViewModel.loginError.observe(this) { errorMessage ->
            Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(loginRequest: LoginRequest) {
        loginViewModel.login(loginRequest)
    }
}