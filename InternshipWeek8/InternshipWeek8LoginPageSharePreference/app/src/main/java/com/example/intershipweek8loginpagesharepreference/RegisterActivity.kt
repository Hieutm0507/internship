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
import com.example.intershipweek8loginpagesharepreference.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var bindingReg : ActivityRegisterBinding
    private lateinit var registerAcc : SharedPreferences
    private var username : String? = null
    private var email : String? = null
    private var passwd : String? = null
    private var listAcc : MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingReg = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindingReg.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getData()

        bindingReg.tvLogin.setOnClickListener {
            backToLogin()
        }

        bindingReg.btSignup.setOnClickListener {
            when {
                bindingReg.etUsername.text.toString().isEmpty() -> bindingReg.etUsername.error = "This field can not be empty"
                bindingReg.etEmail.text.toString().isEmpty() -> bindingReg.etEmail.error = "This field can not be empty"
                bindingReg.etPassword.text.toString().isEmpty() -> bindingReg.etPassword.error = "This field can not be empty"
                bindingReg.etPassword.text.toString() != bindingReg.etCfPassword.text.toString() -> bindingReg.etCfPassword.error = "Password must be matched"
                listAcc.any { it.username == bindingReg.etUsername.text.toString() } -> bindingReg.etUsername.error = "Username already exists"
                listAcc.any { it.email == bindingReg.etEmail.text.toString() } -> bindingReg.etEmail.error = "Account with this email already exists"
                else -> {
                    Toast.makeText(this, "Register successfully", LENGTH_SHORT).show()
                    registerAcc()
                    backToLogin()
                }
            }
        }
    }

    private fun getData() {
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
    private fun registerAcc() {
        registerAcc = this.getSharedPreferences("userAccount", Context.MODE_PRIVATE)
        username = bindingReg.etUsername.text.toString()
        email = bindingReg.etEmail.text.toString()
        passwd = bindingReg.etPassword.text.toString()

        val editor = registerAcc.edit()
        editor.putString("KEY_$username", "${email}__${passwd}__${username}")                    // TODO: Sử dụng "__" để tránh trường hợp username hay email có kí tự _
        editor.apply()
    }

    private fun backToLogin() {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        startActivity(intent)
    }
}