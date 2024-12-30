package com.example.internshipweek6recycleview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshipweek6recycleview.adapter.NhanVienAdapter
import com.example.internshipweek6recycleview.databinding.ActivityMainBinding
import com.example.internshipweek6recycleview.model.NhanVien

class MainActivity : AppCompatActivity(), AddFragment.OnDataPass {
    private lateinit var toolbar: Toolbar
    private lateinit var bindingMain : ActivityMainBinding
    private lateinit var nhanVienAdapter: NhanVienAdapter
    private lateinit var mListNV: MutableList<NhanVien>
    private lateinit var searchView: SearchView
    private lateinit var searchList: MutableList<NhanVien>
    private val fragmentManager = supportFragmentManager

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // ToolBar
        toolbar = bindingMain.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""    // to hide the default title of ToolBar

        mListNV = mutableListOf()
        searchList = mListNV.toMutableList()

        // List of Employee
        val nhanVien1 = NhanVien("anhnq", "Nguyễn Quang Anh", "Marketing", "Chính thức")
        mListNV.add(nhanVien1)
        val nhanVien2 = NhanVien("linhnn", "Nguyễn Ngọc Linh", "Design", "Chính thức")
        mListNV.add(nhanVien2)
        val nhanVien3 = NhanVien("phucnv", "Nguyễn Văn Phúc", "Marketing", "Chính thức")
        mListNV.add(nhanVien3)
        val nhanVien4 = NhanVien("thanhnd", "Nguyễn Duy Thành", "Design", "Chính thức")
        mListNV.add(nhanVien4)
        val nhanVien5 = NhanVien("minhth", "Tạ Hiểu Minh", "Dev", "Thực tập")
        mListNV.add(nhanVien5)
        val nhanVien6 = NhanVien("trungnt", "Nguyễn Thành Trung", "Kinh doanh", "Chính thức")
        mListNV.add(nhanVien6)
        val nhanVien7 = NhanVien("anhnm", "Nguyễn Minh Anh", "Marketing", "Thực tập")
        mListNV.add(nhanVien7)
        val nhanVien8 = NhanVien("thangnv", "Nguyễn Văn Thắng", "Dev", "Chính thức")
        mListNV.add(nhanVien8)
        val nhanVien9 = NhanVien("huongmh", "Nguyễn Minh Hương", "Dev", "Chính thức")
        mListNV.add(nhanVien9)
        val nhanVien10 = NhanVien("huynt", "Nguyễn Thế Huy", "Marketing", "Thực tập")
        mListNV.add(nhanVien10)
        val nhanVien11 = NhanVien("anhpn", "Phan Ngọc Anh", "Kinh doanh", "Thực tập")
        mListNV.add(nhanVien11)
        val nhanVien12 = NhanVien("chaupm", "Phạm Minh Châu", "Kinh doanh", "Thực tập")
        mListNV.add(nhanVien12)
        val nhanVien13 = NhanVien("thanhnd", "Ngô Đình Thành", "Dev", "Thực tập")
        mListNV.add(nhanVien13)
        val nhanVien14 = NhanVien("hoangnm", "Nguyễn Minh Hoàng", "Kinh doanh", "Chính thức")
        mListNV.add(nhanVien14)
        val nhanVien15 = NhanVien("nguyennp", "Nguyễn Phúc Nguyên", "Design", "Chính thức")
        mListNV.add(nhanVien15)
        val nhanVien16 = NhanVien("anhmn", "Mai Ngọc Ánh", "Design", "Chính thức")
        mListNV.add(nhanVien16)
        val nhanVien17 = NhanVien("anhpd", "Phạm Đức Anh", "Dev", "Chính thức")
        mListNV.add(nhanVien17)
        val nhanVien18 = NhanVien("giangnv", "Nguyễn Văn Giang", "Dev", "Thực tập")
        mListNV.add(nhanVien18)
        val nhanVien19 = NhanVien("phatnt", "Ngô Thành Phát", "Kinh doanh", "Chính thức")
        mListNV.add(nhanVien19)
        val nhanVien20 = NhanVien("minhnt", "Nguyễn Tùng Minh", "Design", "Chính thức")
        mListNV.add(nhanVien20)

        val adapter = NhanVienAdapter(searchList)
        searchList.addAll(mListNV)                  // To display the mListNV at the beginning
        nhanVienAdapter = adapter

        // Event Listeners for items in RecyclerView
        adapter.setOnClickListener(object : NhanVienAdapter.OnItemClickListener {
            // Direct and sent data to InfoActivity
            override fun onItemClick(position: Int) {
                val employee: NhanVien = mListNV[position]
                Log.d("TAG_GET_IN4", "${employee.name}, ${employee.department}")

                val intent = Intent(this@MainActivity, InfoActivity::class.java)
                intent.putExtra("EXTRA_SEND_NAME", employee.name)
                intent.putExtra("EXTRA_SEND_USERNAME", employee.id)
                intent.putExtra("EXTRA_SEND_DEPARTMENT", employee.department)
                intent.putExtra("EXTRA_SEND_STATE", employee.state)
                startActivity(intent)
                //Toast.makeText(this@MainActivity, "Clicked on $position", Toast.LENGTH_SHORT).show()
            }

            // Delete Item
            override fun deleteItem(item: NhanVien) {
//                mListNV.removeAt(item)
                mListNV.removeIf { it.id == item.id }
                adapter.submitList(mListNV)
                searchList.removeIf { it.id == item.id }
                adapter.submitList(searchList)
            }

            // Checked
            // TODO: Click on CheckBox will display Select_ToolBar
            @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
            override fun onChosenListChangeListener(chosenList: MutableList<NhanVien>) {
                if (chosenList.isEmpty()) {
                    bindingMain.tbSelect.visibility = View.GONE
                }
                else {
                    bindingMain.tbSelect.visibility = View.VISIBLE
                }

                // Count Item
                bindingMain.tvCountSelected.text = "${chosenList.count()} selected"

                // Activate Close in Select_ToolBar
                bindingMain.ivClose.setOnClickListener {
                    bindingMain.tbSelect.visibility = View.GONE
                    adapter.deSelectAll()
                }

                // Activate Delete Selected Items in Select_ToolBar
                bindingMain.ivDelete.setOnClickListener {
                    AlertDialog.Builder(this@MainActivity).setTitle("Xoá nhân viên")
                        .setIcon(R.drawable.ic_warning)
                        .setMessage("Bạn chắc chắn muốn xoá ${chosenList.count()} nhân viên này?")
                        .setPositiveButton("Có") { dialog,_ ->
                            for (item in chosenList) {
                                mListNV.removeIf { it.id == item.id }
                                searchList.removeIf { it.id == item.id }
                                adapter.submitList(mListNV)
                                adapter.submitList(searchList)
                                adapter.setListNull()
                            }
                            dialog.dismiss()
                        }
                        .setNegativeButton("Không") {dialog,_ ->
                            dialog.dismiss()
                        }
                        .create().show()
                    bindingMain.tbSelect.visibility = View.GONE
                }

                // Activate SelectAll in Select_ToolBar
                bindingMain.ivSelectAll.setOnClickListener {
                    adapter.selectAll()
                }
            }

        })

        val linearLayoutManager = LinearLayoutManager(this)
        bindingMain.rvListNv.layoutManager = linearLayoutManager
        bindingMain.rvListNv.adapter = nhanVienAdapter

        // Search Bar
        searchView = bindingMain.svSearchBar
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {

                }
                val searchText = newText!!.lowercase()
                searchList.clear()
                if (searchText.isNotEmpty()) {
                    mListNV.forEach {
                        if (it.name.lowercase().contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                }
                else {
                    searchList.addAll(mListNV)
                }
                nhanVienAdapter.notifyDataSetChanged()
                return true
            }
        })

        // Activate SEARCH Button
        bindingMain.ivSearch.setOnClickListener {
            bindingMain.svSearchBar.requestFocus()
        }

        // Activate ADD Button
        bindingMain.btAdd.setOnClickListener {
            val ft = fragmentManager.beginTransaction()
            ft.add(R.id.fl_add_employee, AddFragment())
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    // Receive Data from Add Fragment
    @SuppressLint("NotifyDataSetChanged")
    override fun onDataPass(name: String, username: String, department: String, state: String) {
        if (mListNV.any { it.id == username }) {
            Toast.makeText(this, "Username đã tồn tại!!!", Toast.LENGTH_SHORT).show()
        }
        else {
            mListNV.add(NhanVien(username, name, department, state))
            searchList.add(NhanVien(username, name, department, state))
            //Toast.makeText(this, "$name, $username, $department, $state", Toast.LENGTH_SHORT).show()

            nhanVienAdapter.notifyDataSetChanged()
        }

//        mListNV.add(NhanVien(username, name, department, state))
//        searchList.add(NhanVien(username, name, department, state))
//        //Toast.makeText(this, "$name, $username, $department, $state", Toast.LENGTH_SHORT).show()
//
//        nhanVienAdapter.notifyDataSetChanged()

    }
}
