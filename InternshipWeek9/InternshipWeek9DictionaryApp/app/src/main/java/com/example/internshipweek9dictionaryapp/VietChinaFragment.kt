package com.example.internshipweek9dictionaryapp

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.internshipweek9dictionaryapp.databinding.FragmentVietChinaBinding
import com.example.internshipweek9dictionaryapp.model.VietChina

@Suppress("DEPRECATION")
class VietChinaFragment : Fragment() {
    private lateinit var binding : FragmentVietChinaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVietChinaBinding.inflate(inflater, container, false)

        val mObject = arguments?.getParcelable<VietChina>("SEND_VIET_CHINA")
        if (mObject != null) {
            binding.tvWord.text = mObject.word
            binding.tvContent.text = Html.fromHtml(mObject.content, Html.FROM_HTML_MODE_LEGACY)
                // Because mObject.content contains HTML syntax
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