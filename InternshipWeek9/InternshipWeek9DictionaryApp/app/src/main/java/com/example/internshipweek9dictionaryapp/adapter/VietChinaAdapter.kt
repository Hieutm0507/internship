package com.example.internshipweek9dictionaryapp.adapter

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipweek9dictionaryapp.databinding.ItemVietChinaBinding
import com.example.internshipweek9dictionaryapp.model.VietChina

class VietChinaAdapter(private var listVietChina: List<VietChina> = listOf()) : RecyclerView.Adapter<VietChinaAdapter.DictViewHolder> () {
    private var mListener : OnItemClickListener? = null

    inner class DictViewHolder(val binding : ItemVietChinaBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val selectedItem = getItem(adapterPosition)
                if (selectedItem != null) {
                    mListener?.onItemClick(selectedItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictViewHolder {
        val binding = ItemVietChinaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DictViewHolder(binding)
    }

    override fun getItemCount(): Int = listVietChina.size

    override fun onBindViewHolder(holder: DictViewHolder, position: Int) {
        val currentWord = listVietChina[position]

        val spanned = Html.fromHtml(currentWord.content, Html.FROM_HTML_MODE_LEGACY)

        holder.binding.tvChinese.text = spanned
        holder.binding.tvViet.text = currentWord.word
    }

    fun setOnClickListener( listener : OnItemClickListener) {
        mListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<VietChina>) {
        listVietChina = newItems
        notifyDataSetChanged()
    }

    fun getItem(position: Int): VietChina? {
        return if (position in listVietChina.indices) listVietChina[position] else null
    }

    interface OnItemClickListener {
        fun onItemClick(item : VietChina)
    }
}