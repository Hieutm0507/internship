package com.example.internshipweek9dictionaryapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipweek9dictionaryapp.databinding.ItemChineseBinding
import com.example.internshipweek9dictionaryapp.model.Chinese

class ChineseAdapter(private var listChinese: List<Chinese> = listOf()) : RecyclerView.Adapter<ChineseAdapter.DictViewHolder> () {
    private var mListener : OnItemClickListener? = null

    inner class DictViewHolder(val binding : ItemChineseBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                mListener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictViewHolder {
        val binding = ItemChineseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DictViewHolder(binding)
    }

    override fun getItemCount(): Int = listChinese.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DictViewHolder, position: Int) {
        val currentWord = listChinese[position]

        holder.binding.tvChinese.text = currentWord.han
        holder.binding.tvViet.text = currentWord.viet
        holder.binding.tvPinyin.text = "[ ${currentWord.pinyin} ]"
    }

    fun setOnClickListener( listener : OnItemClickListener) {
        mListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<Chinese>) {
        listChinese = newItems
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position : Int)
    }
}