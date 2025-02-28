package com.example.internshipweek5

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.internshipweek5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "BONJOUR"
    }
    // Define variables
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    private var getPreResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val preResult = result.data
            preResult?.let {
                val bmi = it.getDoubleExtra("Pre BMI", 0.0)
                val type = it.getStringExtra("Pre Type")
                Log.d("PRERESULT", "$bmi  $type")
                binding.etPreResult.setText("Chỉ số BMI: $bmi.\nThuộc loại: $type.")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        enableEdgeToEdge()

        binding.btGuiKQ.setOnClickListener {
            // Lấy giá trị từ EditText
            val sendHeight = binding.etHeight.text.toString().toInt()
            //  Method 2
            //  val inputHeight = height.text.toString()
            //  val sendHeight = Integer.parseInt(inputHeight)
            val sendWeight = binding.etWeight.text.toString().toInt()
            Log.d(TAG,"$sendHeight $sendWeight")

            // Tạo intent để gửi data
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("Send Height", sendHeight)
            intent.putExtra("Send Weight", sendWeight)
            getPreResult.launch(intent)
        }
    }
}