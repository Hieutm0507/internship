package com.example.internshipweek5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.internshipweek5.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var bindingResult: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindingResult = ActivityResultBinding.inflate(layoutInflater)
        setContentView(bindingResult.root)

        // Nhận HEIGHT, WEIGHT và tính BMI
        val intentReceive = intent
        val receiveHeight = intentReceive.getIntExtra("Send Height", 0)
        val receiveWeight = intentReceive.getIntExtra("Send Weight", 0)
        Log.d("NHẬN SỐ LIỆU", "$receiveHeight, $receiveWeight")
        val bmi = tinhBMI(receiveHeight, receiveWeight)
        bindingResult.tvResultBmi.text = "$bmi"

        // Đánh giá chỉ số BMI
        val type = danhGiaBMI(bmi)
        bindingResult.tvResultType.text = type


        // Quay lại
        bindingResult.btGoBack.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("Pre BMI", bmi)
            resultIntent.putExtra("Pre Type", type)
            setResult(RESULT_OK, resultIntent)
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