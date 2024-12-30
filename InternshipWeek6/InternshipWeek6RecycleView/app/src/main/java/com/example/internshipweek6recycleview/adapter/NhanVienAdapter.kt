package com.example.internshipweek6recycleview.adapter

import android.annotation.SuppressLint
import android.util.Log
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
    private var mListNV: MutableList<NhanVien> = mutableListOf(),
    private var chosenList: MutableList<NhanVien> = mutableListOf()
) : RecyclerView.Adapter<NhanVienAdapter.NhanVienHolder>() {
    private var mListener: OnItemClickListener? = null

    fun setOnClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun setListNull() {
        chosenList = mutableListOf()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(listNV: MutableList<NhanVien>) {
        mListNV = listNV
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun selectAll() {
        for (item in mListNV) {
            item.isSelect = true
//            if (!chosenList.contains(item)) {
//                chosenList.add(item)
//            }
        }
        chosenList.clear()
        chosenList.addAll(mListNV)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deSelectAll() {
        for (item in mListNV) {
            item.isSelect = false
        }
        chosenList.clear()
        notifyDataSetChanged()
    }

    inner class NhanVienHolder(val binding: ItemNhanVienBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            // Display the information of an Employee
            binding.root.setOnClickListener {
                mListener?.onItemClick(adapterPosition)
            }

            val ivMore = binding.ivMore
            ivMore.setOnClickListener {
                popupMenus(it)
            }

            itemView.setOnLongClickListener {
                ivMore.performClick()
            }

            // CheckBox Event Listener
            binding.cbSelect.setOnCheckedChangeListener { _, isSelect ->
                val chosenItem = mListNV[adapterPosition]
                chosenItem.isSelect = isSelect
                if (isSelect) {
                    chosenList.add(chosenItem)
                }
                else {
                    chosenList.removeIf { it.id == chosenItem.id }
                }
                mListener?.onChosenListChangeListener(chosenList)
                Log.d("TAG_CHOSEN_LIST", chosenList.toString())
            }
        }

        // PopUp Menu for each items
        private fun popupMenus(view: View) {
            val popupMenus = PopupMenu(itemView.context, view)
            popupMenus.inflate(R.menu.item_more)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_employee -> {
//                        Toast.makeText(itemView.context, "Clicked on Edit $adapterPosition", Toast.LENGTH_SHORT).show()


                        true
                    }
                    R.id.delete_employee -> {
                        AlertDialog.Builder(itemView.context).setTitle("Xoá Nhân Viên")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Bạn chắc chắn muốn xoá nhân viên này?")
                            .setPositiveButton("Có") { dialog, _ ->
                                mListener?.deleteItem(mListNV[adapterPosition])
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
        return NhanVienHolder(binding)
    }

    override fun getItemCount(): Int = mListNV.size

    override fun onBindViewHolder(holder: NhanVienHolder, position: Int) {
        val nhanVien: NhanVien = mListNV[position]

        // Attach data to container
        holder.binding.tvTenNv.text = nhanVien.name
        holder.binding.tvUsername.text = nhanVien.id

        when(nhanVien.department) {
            "Marketing" -> holder.binding.ivDepartment.setImageResource(R.drawable.ic_mkt)
            "Dev" -> holder.binding.ivDepartment.setImageResource(R.drawable.ic_dev)
            "Design" -> holder.binding.ivDepartment.setImageResource(R.drawable.ic_des)
            "Kinh doanh" -> holder.binding.ivDepartment.setImageResource(R.drawable.ic_business)
        }

        when(nhanVien.state) {
            "Chính thức" -> holder.binding.ivState.setImageResource(R.drawable.ic_official)
            "Thực tập" -> holder.binding.ivState.setImageResource(R.drawable.ic_intern)
        }

        holder.binding.cbSelect.isChecked = nhanVien.isSelect
        holder.binding.cbSelect.setOnCheckedChangeListener { _, isSelect ->
            nhanVien.isSelect = isSelect
            if (isSelect) {
                chosenList.add(nhanVien)
            }
            else {
                chosenList.removeIf { it.id == nhanVien.id }
            }
            mListener?.onChosenListChangeListener(chosenList)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun deleteItem(item: NhanVien)
        fun onChosenListChangeListener (chosenList: MutableList<NhanVien>)
    }
}