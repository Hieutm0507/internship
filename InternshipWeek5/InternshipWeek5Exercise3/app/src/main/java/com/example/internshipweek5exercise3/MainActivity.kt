package com.example.internshipweek5exercise3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.internshipweek5exercise3.databinding.ActivityMainBinding
import com.example.internshipweek5exercise3.databinding.FragmentMultipleBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMultiple : ActivityMainBinding
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindingMultiple = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMultiple.root)

//        // Pass data vào Fragment dùng BUNDLE
//        val inputNum = bindingMultiple.etInputNum.text.toString().toDoubleOrNull()
//        val bundle = Bundle()
//        if (inputNum != null) {
//            bundle.putDouble("BUNDLE_SEND_INPUT", inputNum)
//        }
//        else Toast.makeText(this, "Hãy nhập số hợp lệ", Toast.LENGTH_SHORT).show()

        // Activate 3 buttons
        bindingMultiple.btMultiple.setOnClickListener {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.fl_fragment_display, MultipleFragment())
            ft.addToBackStack(null)
            ft.commit()

            // Pass data vào Fragment dùng BUNDLE
            val inputNum = bindingMultiple.etInputNum.text.toString().toDoubleOrNull()
            Log.d("LOG_INPUT_NUM", "$inputNum")

            if (inputNum != null) {
                val bundle = Bundle().apply {
                    putDouble("BUNDLE_SEND_INPUT", inputNum)
                }
                MultipleFragment().arguments = bundle
            }
            else Toast.makeText(this, "Hãy nhập số hợp lệ", Toast.LENGTH_SHORT).show()
        }

        bindingMultiple.btDivision.setOnClickListener {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.fl_fragment_display, DivisionFragment())
            ft.addToBackStack(null)
            ft.commit()
        }

        bindingMultiple.btSquare.setOnClickListener {
            val ft = fragmentManager.beginTransaction()
            ft.add(R.id.fl_fragment_display, SquareFragment())
            ft.addToBackStack(null)
            ft.commit()
        }



//        // Pass input cho MultipleFragment
//        bindingMultiple.fmMultiple.setOnClickListener{
//            if (inputNum != null) {
//                val mFragmentManager = supportFragmentManager
//                val mFragmentTransaction = mFragmentManager.beginTransaction()
//                val mFragment = MultipleFragment()
//
//                Log.d("INPUTNUM", "$inputNum")
//                val mBundle = Bundle()
//                mBundle.putDouble("BUNDLE_SEND_INPUT", inputNum)
//                mFragment.arguments = mBundle
//                mFragmentTransaction.add(R.id.fm_multiple , mFragment).commit()
//            }
//            else Toast.makeText(this, "Hãy nhập số hợp lệ", Toast.LENGTH_SHORT).show()
//
//        }
    }
}