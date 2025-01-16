package com.example.intershipweek8loginpagesharepreference

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intershipweek8loginpagesharepreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharePreferences: SharedPreferences
    private var saveEmail : String? = ""
    private var savePasswd : String? = ""
    private var rememberMe: Boolean? = null
    private lateinit var registerAcc: SharedPreferences
    private var listAcc : MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getUserList()

        binding.btLogin.setOnClickListener {
            Log.d("TAG_SEARCH", listAcc.toString())
            val account = listAcc.find { it.email == binding.etEmail.text.toString() }
            Log.d("TAG_SEARCH_ACC", account.toString())
            if (account == null) {
                binding.etEmail.error = "Account does not exist"
            }
            else {
                if (binding.etPassword.text.toString() == account.passwd) {
                    Toast.makeText(this, "Login Successfully", LENGTH_SHORT).show()
                }
                else {
                    binding.etPassword.error = "Incorrect username or password"
                }
            }
        }

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

    private fun getUserList() {
        registerAcc = this.getSharedPreferences("userAccount", Context.MODE_PRIVATE)
        val data: Map<String, *> = registerAcc.all
        for ((key,value) in data) {
            Log.d("TAG_VALUE", value.toString())
            val arr = value.toString().split("__")
            val acc = User(arr[0], arr[1], arr[2], key)
            listAcc.add(acc)
            Log.d("TAG_LIST", listAcc.toString())
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun saveData() {
        sharePreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
            /* name: tên file lưu dữ liệu
             * mode: sử dụng MODE_PRIVATE: cấp quyền truy cập nội tại (chỉ bên trong app truy cập được) */
        saveEmail = binding.etEmail.text.toString()
        savePasswd = binding.etPassword.text.toString()
        rememberMe = binding.cbRememberMe.isChecked

        // TODO: Save the Data      (KEY MUST BE UNIQUE)
        val editor = sharePreferences.edit()
        editor.putString("KEY_EMAIL", saveEmail)
        editor.putString("KEY_PASSWD", savePasswd)
        editor.putBoolean("KEY_RMB", rememberMe!!)              // (Kiến thức cũ) !! để ko null
        editor.apply()
        Toast.makeText(this, "Information saved successfully", LENGTH_SHORT).show()
    }

    private fun callData() {
        sharePreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        saveEmail = sharePreferences.getString("KEY_EMAIL", null)
        savePasswd = sharePreferences.getString("KEY_PASSWD", null)
        rememberMe = sharePreferences.getBoolean("KEY_RMB", false)

        binding.etEmail.setText(saveEmail)
        binding.etPassword.setText(savePasswd)
        binding.cbRememberMe.isChecked = rememberMe!!
    }
}