package com.example.internshipweek6recycleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipweek6recycleview.R
import com.example.internshipweek6recycleview.databinding.ItemNhanVienBinding
import com.example.internshipweek6recycleview.model.NhanVien

class NhanVienAdapter(
    private val mListNV: List<NhanVien> = mutableListOf(),
) : RecyclerView.Adapter<NhanVienAdapter.NhanVienHolder>() {
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class NhanVienHolder(val binding: ItemNhanVienBinding, listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        // Display the information of an Employee
        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            val ivMore = binding.ivMore
            ivMore.setOnClickListener {
                popupMenus(it)
            }
        }

        private fun popupMenus(view: View) {
            val popupMenus = PopupMenu(itemView.context, view)
            popupMenus.inflate(R.menu.item_more)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_employee -> {
                        true
                    }
                    R.id.delete_employee -> {
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NhanVienHolder {
        val binding = ItemNhanVienBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NhanVienHolder(binding, mListener)
    }

    override fun getItemCount(): Int = mListNV.size

    override fun onBindViewHolder(holder: NhanVienHolder, position: Int) {
        val nhanVien: NhanVien = mListNV[position]

        // Attach data to container
        holder.binding.tvTenNv.text = nhanVien.name
        holder.binding.tvUsername.text = nhanVien.id
    }
}