package com.example.internshipweek5

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.internshipweek5.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var bindingResult: ActivityResultBinding

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {uri: Uri? ->

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindingResult = ActivityResultBinding.inflate(layoutInflater)
        setContentView(bindingResult.root)

        // Nhận HEIGHT, WEIGHT và tính BMI
        val intent = intent
        val receiveHeight = intent.getIntExtra("Send Height", 0)
        val receiveWeight = intent.getIntExtra("Send Weight", 0)
        Log.d("NHẬN SỐ LIỆU", "$receiveHeight, $receiveWeight")
        val bmi = tinhBMI(receiveHeight, receiveWeight)
        bindingResult.tvResultBmi.text = "$bmi"

        // Đánh giá chỉ số BMI
        bindingResult.tvResultType.text = danhGiaBMI(bmi)

        // Quay lại
        bindingResult.btGoBack.setOnClickListener {
            finish()
        }

    }

    private fun tinhBMI(height: Int, weight:Int):Double {
        val getHeight = height.toDouble()
        val getWeight = weight.toDouble()

        val bmi = getWeight/(getHeight*getHeight/10000.0)
        return (Math.round(bmi*100.0)/100.0)
    }

    private fun danhGiaBMI(bmi:Double): String {
        val result : String = if (bmi < 18.5) {
            "Thiếu cân"
        } else if (bmi >= 18.5 && bmi < 23.0) {
            "Bình thường"
        } else if (bmi >= 23.0 && bmi < 25.0)
            "Thừa cân"
        else if (bmi >= 25.0 && bmi < 30.0)
            "Béo phì độ 1"
        else
            "Béo phì độ 2"

        return result
    }
}