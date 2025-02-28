package com.example.internshipfinalinstagram.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipfinalinstagram.databinding.ItemImageBinding

class PreviewImgAdapter(private val imageUrls: List<Uri>) : RecyclerView.Adapter<PreviewImgAdapter.PreviewImgHolder>() {

    inner class PreviewImgHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewImgHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PreviewImgHolder(binding)
    }

    override fun onBindViewHolder(holder: PreviewImgHolder, position: Int) {
        holder.binding.ivPostImage.setImageURI(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size
}