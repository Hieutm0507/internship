package com.example.internshipweek9dictionaryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipweek9dictionaryapp.databinding.ItemChineseBinding
import com.example.internshipweek9dictionaryapp.model.Chinese

class DictAdapter(private val listChinese: List<Chinese> = listOf()) : RecyclerView.Adapter<DictAdapter.DictViewHolder> () {

    inner class DictViewHolder(val binding : ItemChineseBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictViewHolder {
        val binding = ItemChineseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DictViewHolder(binding)
    }

    override fun getItemCount(): Int = listChinese.size

    override fun onBindViewHolder(holder: DictViewHolder, position: Int) {
        val currentWord = listChinese[position]

        holder.binding.tvChinese.text = currentWord.han
        holder.binding.tvViet.text = currentWord.viet
    }
}