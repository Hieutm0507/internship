package com.example.internshipweek7coroutine

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internshipweek7coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private var countPlus = 0
    private var countMinus = 0
    private var countMulti = 1
    private var countRandom = 0
    private lateinit var plusScope: CoroutineScope
    private lateinit var minusScope: CoroutineScope
    private lateinit var multiplyScope: CoroutineScope
    private lateinit var randomScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //        window.statusBarColor

        // Toolbar
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""        // Xoá đi tên app mặc định

        // Sử dụng Coroutines
        usingCoroutine()
        minusCoroutine()
        multiplyCoroutine()
        randomCoroutine()

        // On Click Listeners
        binding.btStopAdd.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btPlayAdd.visibility = View.VISIBLE

            if (::plusScope.isInitialized && plusScope.isActive) {
                plusScope.cancel()
            }
        }

        binding.btPlayAdd.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btStopAdd.visibility = View.VISIBLE

            if (::plusScope.isInitialized || !plusScope.isActive) {
                usingCoroutine()
            }
        }

        binding.btStopMinus.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btPlayMinus.visibility = View.VISIBLE

            if (::minusScope.isInitialized && minusScope.isActive) {
                minusScope.cancel()
            }
        }

        binding.btPlayMinus.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btStopMinus.visibility = View.VISIBLE

            if (::minusScope.isInitialized || !minusScope.isActive) {
                minusCoroutine()
            }
        }

        binding.btStopMultiply.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btPlayMultiply.visibility = View.VISIBLE

            if (::multiplyScope.isInitialized && multiplyScope.isActive) {
                multiplyScope.cancel()
            }
        }

        binding.btPlayMultiply.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btStopMultiply.visibility = View.VISIBLE

            if (::multiplyScope.isInitialized || !multiplyScope.isActive) {
                multiplyCoroutine()
            }
        }

        binding.btStopRandom.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btPlayRandom.visibility = View.VISIBLE

            if (::randomScope.isInitialized && randomScope.isActive) {
                randomScope.cancel()
            }
        }

        binding.btPlayRandom.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btStopRandom.visibility = View.VISIBLE

            if (::randomScope.isInitialized || !randomScope.isActive) {
                randomCoroutine()
            }
        }
    }


    // SET UP COROUTINE
    // TODO: Sử dụng Dispatchers.Main (vì pass giá trị vào các View)

    private fun usingCoroutine() {
        plusScope = CoroutineScope(Dispatchers.Main)
        plusScope.launch {
            while (isActive) {
                binding.tvValueAdd.text = countPlus.toString()
                countPlus++
                delay(1000L)        // L stands for LONG
            }
        }
    }

    private fun minusCoroutine() {
        minusScope = CoroutineScope(Dispatchers.Main)
        minusScope.launch {
            while (isActive) {
                binding.tvValueMinus.text = countMinus.toString()
                countMinus--
                delay(1000L)
            }
        }
    }

    private fun multiplyCoroutine() {
        multiplyScope = CoroutineScope(Dispatchers.Main)
        multiplyScope.launch {
            while (isActive) {
                binding.tvValueMultiply.text = countMulti.toString()
                countMulti *= 2
                delay(5000L)
            }
        }
    }

    private fun randomCoroutine() {
        randomScope = CoroutineScope(Dispatchers.Main)
        randomScope.launch {
            while (isActive) {
                binding.tvValueRandom.text = countRandom.toString()
                countRandom = Random.nextInt()
                delay(3000L)
            }
        }
    }
}