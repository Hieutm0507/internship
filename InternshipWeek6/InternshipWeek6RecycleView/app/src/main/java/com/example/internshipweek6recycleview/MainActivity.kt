package com.example.internshipweek6recycleview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshipweek6recycleview.adapter.NhanVienAdapter
import com.example.internshipweek6recycleview.databinding.ActivityMainBinding
import com.example.internshipweek6recycleview.model.NhanVien

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var bindingMain : ActivityMainBinding
    private lateinit var nhanVienAdapter: NhanVienAdapter
    private lateinit var mListNV: MutableList<NhanVien>
    private lateinit var searchView: SearchView
    private lateinit var searchList: List<NhanVien>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // ToolBar
        toolbar = bindingMain.toolbar
        setSupportActionBar(toolbar)

        mListNV = mutableListOf()

        // List of Employee
        val nhanVien1 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien1)
        val nhanVien2 = NhanVien("linhnn", "Nguyễn Ngọc Linh", "Design", "Chính thức")
        mListNV.add(nhanVien2)
        val nhanVien3 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien3)
        val nhanVien4 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien4)
        val nhanVien5 = NhanVien("minhth", "Tạ Hiểu Minh", "Dev", "Thực tập")
        mListNV.add(nhanVien5)
        val nhanVien6 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien6)
        val nhanVien7 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien7)
        val nhanVien8 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien8)
        val nhanVien9 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien9)
        val nhanVien10 = NhanVien("", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien10)
        val nhanVien11 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien11)
        val nhanVien12 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien12)
        val nhanVien13 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien13)
        val nhanVien14 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien14)
        val nhanVien15 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien15)
        val nhanVien16 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien16)
        val nhanVien17 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien17)
        val nhanVien18 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien18)
        val nhanVien19 = NhanVien("phatnt", "Ngô Thành Phát", "Kinh doanh", "Chính thức")
        mListNV.add(nhanVien19)
        val nhanVien20 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien20)

        val adapter = NhanVienAdapter(mListNV)
        nhanVienAdapter = adapter

        // Direct and sent data to InfoActivity
        adapter.setOnClickListener(object : NhanVienAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val employee: NhanVien = mListNV[position]
                Log.d("TAG_GET_IN4", "${employee.name}, ${employee.department}")

                val intent = Intent(this@MainActivity, InfoActivity::class.java)
                intent.putExtra("EXTRA_SEND_NAME", employee.name)
                intent.putExtra("EXTRA_SEND_USERNAME", employee.id)
                intent.putExtra("EXTRA_SEND_DEPARTMENT", employee.department)
                intent.putExtra("EXTRA_SEND_STATE", employee.state)
                startActivity(intent)
                Toast.makeText(this@MainActivity, "Clicked on $position", Toast.LENGTH_SHORT).show()
            }
        })

        val linearLayoutManager = LinearLayoutManager(this)
        bindingMain.rvListNv.layoutManager = linearLayoutManager
        bindingMain.rvListNv.adapter = nhanVienAdapter

        // Activate ADD Button
        bindingMain.btAdd.setOnClickListener {

        }
    }
}
