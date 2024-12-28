package com.example.internshipweek6recycleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipweek6recycleview.databinding.ItemNhanVienBinding
import com.example.internshipweek6recycleview.model.NhanVien

class NhanVienAdapter(
    val mListNV: List<NhanVien> = mutableListOf(),
) : RecyclerView.Adapter<NhanVienAdapter.NhanVienHolder>() {

    class NhanVienHolder(val binding: ItemNhanVienBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NhanVienHolder {
        val binding = ItemNhanVienBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NhanVienHolder(binding)
    }

    override fun getItemCount(): Int = mListNV.size

    override fun onBindViewHolder(holder: NhanVienHolder, position: Int) {
        val nhanVien: NhanVien = mListNV[position]

        // Attach data to container
        holder.binding.tvTenNv.text = nhanVien.ten
        holder.binding.tvUsername.text = nhanVien.id
    }
}