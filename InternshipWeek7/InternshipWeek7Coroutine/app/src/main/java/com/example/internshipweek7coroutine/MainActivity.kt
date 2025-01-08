package com.example.internshipweek7coroutine

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.internshipweek7coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
    private var countMulti = 1L         // Sử dụng Long vì để INT sẽ bị giới hạn đến 2147483647
                                        // => Sau 32 lần thì giá trị sẽ quay về 0
    private var countRandom = 0
    private lateinit var plusScopeJob: Job
    private lateinit var minusScopeJob: Job
    private lateinit var multiplyScopeJob: Job
    private lateinit var randomScopeJob: Job


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
        supportActionBar?.title = ""        // Xoá đi tên app default

        // Sử dụng Coroutines
        usingCoroutine()
        minusCoroutine()
        multiplyCoroutine()
        randomCoroutine()


        // TODO: Xử lý sự kiện: On Click Listeners
        binding.btStopAdd.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btPlayAdd.visibility = View.VISIBLE

            if (::plusScopeJob.isInitialized && plusScopeJob.isActive) {
                plusScopeJob.cancel()
            }
        }

        binding.btPlayAdd.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btStopAdd.visibility = View.VISIBLE

            if (::plusScopeJob.isInitialized || !plusScopeJob.isActive) {
                usingCoroutine()
            }
        }

        binding.btStopMinus.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btPlayMinus.visibility = View.VISIBLE

            if (::minusScopeJob.isInitialized && minusScopeJob.isActive) {
                minusScopeJob.cancel()
            }
        }

        binding.btPlayMinus.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btStopMinus.visibility = View.VISIBLE

            if (::minusScopeJob.isInitialized || !minusScopeJob.isActive) {
                minusCoroutine()
            }
        }

        binding.btStopMultiply.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btPlayMultiply.visibility = View.VISIBLE

            if (::multiplyScopeJob.isInitialized && multiplyScopeJob.isActive) {
                multiplyScopeJob.cancel()
            }
        }

        binding.btPlayMultiply.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btStopMultiply.visibility = View.VISIBLE

            if (::multiplyScopeJob.isInitialized || !multiplyScopeJob.isActive) {
                multiplyCoroutine()
            }
        }

        binding.btStopRandom.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btPlayRandom.visibility = View.VISIBLE

            if (::randomScopeJob.isInitialized && randomScopeJob.isActive) {
                randomScopeJob.cancel()
            }
        }

        binding.btPlayRandom.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.btStopRandom.visibility = View.VISIBLE

            if (::randomScopeJob.isInitialized || !randomScopeJob.isActive) {
                randomCoroutine()
            }
        }
    }


    /* SET UP COROUTINE
     * TODO: Sử dụng Dispatchers.Main (vì pass giá trị vào các View)
     * Hướng solve: Sử dụng WHILE loop
    */
    private fun usingCoroutine() {
        // Sử dụng lifecycleScope để đóng luồng khi Activity bị destroy
        plusScopeJob = lifecycleScope.launch(Dispatchers.Main) {
            while (isActive) {
                binding.tvValueAdd.text = countPlus.toString()
                countPlus++
                delay(1000L)        // L stands for LONG
            }
        }

        /* Gán luồng trực tiếp cho CoroutineScope
         * Nên khi MainActivity này destroy thì luồng này chưa bị đóng
         */
//        plusScope = CoroutineScope(Dispatchers.Main)
//        plusScope.launch {
//            while (isActive) {
//                binding.tvValueAdd.text = countPlus.toString()
//                countPlus++
//                delay(1000L)        // L stands for LONG
//            }
//        }
    }

    private fun minusCoroutine() {
        minusScopeJob = lifecycleScope.launch(Dispatchers.Main) {
            while (isActive) {
                binding.tvValueMinus.text = countMinus.toString()
                countMinus--
                delay(1000L)
            }
        }
    }

    private fun multiplyCoroutine() {
        multiplyScopeJob = lifecycleScope.launch(Dispatchers.Main) {
            while (isActive) {
                binding.tvValueMultiply.text = countMulti.toString()
                countMulti *= 2
                delay(5000L)
                Log.d("TAG_CHECK_MULTIPLY", countMulti.toString())
            }
        }
    }

    private fun randomCoroutine() {
        randomScopeJob = lifecycleScope.launch(Dispatchers.Main) {
            while (isActive) {
                binding.tvValueRandom.text = countRandom.toString()
                countRandom = Random.nextInt()
                delay(3000L)
            }
        }
    }
}