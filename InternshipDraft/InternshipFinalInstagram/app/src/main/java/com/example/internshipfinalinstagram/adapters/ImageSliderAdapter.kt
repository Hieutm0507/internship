package com.example.internshipfinalinstagram.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internshipfinalinstagram.databinding.ItemImageBinding

class ImageSliderAdapter(private val imageUrls: List<String>) : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            Glide.with(binding.ivPostImage.context)
                .load(imageUrl)
                .centerCrop()
                .into(binding.ivPostImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size
}
