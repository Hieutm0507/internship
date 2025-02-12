package com.example.internshipweek9dictionaryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.internshipweek9dictionaryapp.databinding.FragmentChineseBinding
import com.example.internshipweek9dictionaryapp.model.Chinese

@Suppress("DEPRECATION")
class ChineseFragment : Fragment() {
    private lateinit var binding : FragmentChineseBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChineseBinding.inflate(inflater, container, false)

        val mObject = arguments?.getParcelable<Chinese>("SEND_CHINESE")
        if (mObject != null) {
            binding.tvHanTu.text = mObject.han
            binding.tvViet.text = mObject.viet
            binding.tvPinyin.text = "[ ${mObject.pinyin} ]"
            binding.tvMean.text = mObject.mean?.replace("\\n", "\n")?.replace("\\t", "\t\t")
                // EXPLAIN: Khi gắn mObject vào text thì app lại auto thêm một slash "\" trước mỗi "\n" và "\t"
                //      Nên ở đây, ta format lại text by replacing value
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btBack.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }
}