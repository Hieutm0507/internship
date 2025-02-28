package com.example.internshipfinalinstagram.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipfinalinstagram.databinding.ItemImageBinding
import com.example.internshipfinalinstagram.models.MediaItem

class MediaAdapter (private var mediaList : List<MediaItem>) : RecyclerView.Adapter<MediaAdapter.MediaHolder>() {
    inner class MediaHolder(val binding : ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaHolder(binding)
    }

    override fun getItemCount(): Int = mediaList.size

    override fun onBindViewHolder(holder: MediaHolder, position: Int) {
        TODO("Not yet implemented")
    }
}