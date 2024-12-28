package com.example.internshipweek6recycleview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipweek6recycleview.R
import com.example.internshipweek6recycleview.databinding.ItemNhanVienBinding
import com.example.internshipweek6recycleview.model.NhanVien

class NhanVienAdapter(
    private val mListNV: MutableList<NhanVien> = mutableListOf(),
) : RecyclerView.Adapter<NhanVienAdapter.NhanVienHolder>() {
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    inner class NhanVienHolder(val binding: ItemNhanVienBinding, listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        // Display the information of an Employee
        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            val ivMore = binding.ivMore
            ivMore.setOnClickListener {
                popupMenus(it)
            }

            itemView.setOnLongClickListener {
                ivMore.performClick()
            }
        }

        // PopUp Menu for each items
        @SuppressLint("NotifyDataSetChanged")
        private fun popupMenus(view: View) {
            val popupMenus = PopupMenu(itemView.context, view)
            popupMenus.inflate(R.menu.item_more)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_employee -> {
                        Toast.makeText(itemView.context, "Clicked on Edit $adapterPosition", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.delete_employee -> {
                        AlertDialog.Builder(itemView.context).setTitle("Xoá Nhân Viên")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Bạn chắc chắn muốn xoá nhân viên này?")
                            .setPositiveButton("Có") { dialog, _ ->
                                mListNV.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(itemView.context, "Đã xoá thành công", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("Không") {dialog,_ ->
                                dialog.dismiss()
                            }
                            .create().show()
                        true
                    }
                    else -> false
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