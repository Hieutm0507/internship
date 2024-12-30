package com.example.internshipweek6recycleview

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.internshipweek6recycleview.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private var departmentList = arrayListOf(
        "Dev",
        "Kinh doanh",
        "Design",
        "Marketing"
    )

    private var stateList = arrayListOf(
        "Chính thức",
        "Thực tập"
    )
    private var department : String = "empty"
    private var state : String = "empty"
    private var callback : OnDataPass? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = try {
            context as OnDataPass
        }
        catch (e: ClassCastException) {
            throw ClassCastException("$context phải implement OnDataPass")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Close this Fragment
        binding.ivClose.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        binding.spnDepartment.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, departmentList)
        binding.spnDepartment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                Toast.makeText(requireContext(), departmentList[position], Toast.LENGTH_SHORT).show()
                department = when (position) {
                    0 -> "Dev"
                    1 -> "Kinh doanh"
                    2 -> "Design"
                    3 -> "Marketing"
                    else -> "empty"
                }
                Log.d("TAG_DEPARTMENT", department)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(requireContext(), "Bạn chưa chọn!!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.spnState.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, stateList)
        binding.spnState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                Toast.makeText(requireContext(), stateList[position], Toast.LENGTH_SHORT).show()
                state = when (position) {
                    0 -> "Chính thức"
                    1 -> "Thực tập"
                    else -> "empty"
                }
                Log.d("TAG_STATE", state)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(requireContext(), "Bạn chưa chọn!!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btFinish.setOnClickListener {
            callback?.onDataPass(binding.etName.text.toString(), binding.etUsername.text.toString(), department, state)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    interface OnDataPass {
        fun onDataPass(name: String, username: String, department: String, state: String)
    }

}