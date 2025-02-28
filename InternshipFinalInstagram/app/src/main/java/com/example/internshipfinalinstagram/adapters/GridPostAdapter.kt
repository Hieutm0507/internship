package com.example.internshipfinalinstagram.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internshipfinalinstagram.databinding.ItemImageBinding
import com.example.internshipfinalinstagram.models.PostData

@Suppress("UNREACHABLE_CODE")
class GridPostAdapter(var listPost : List<PostData> = listOf()) : RecyclerView.Adapter<GridPostAdapter.GridPostHolder>() {
    inner class GridPostHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridPostHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.ivPostImage.layoutParams.height = dpToPx(150, binding.root.context)
        binding.ivPostImage.requestLayout()

        return GridPostHolder(binding)
    }

    override fun getItemCount(): Int = listPost.size

    override fun onBindViewHolder(holder: GridPostHolder, position: Int) {
        val currentImg = listPost[position]

        val imgUrl = currentImg.images.first()
        Glide.with(holder.binding.ivPostImage.context).load(imgUrl).centerCrop().into(holder.binding.ivPostImage)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<PostData>) {
        listPost = newList
        notifyDataSetChanged() // Cập nhật RecyclerView
    }

    fun getCurrentData(): List<PostData> {
        return listPost
    }

    fun dpToPx(dp: Int, context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}