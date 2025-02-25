package com.example.internshipfinalinstagram.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipfinalinstagram.databinding.ItemPostBinding
import com.example.internshipfinalinstagram.models.PostData
import com.squareup.picasso.Picasso

class PostAdapter(private var listPost: List<PostData> = listOf()) : RecyclerView.Adapter<PostAdapter.PostHolder>() {
    inner class PostHolder(val binding : ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int = listPost.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val currentPost = listPost[position]

        holder.binding.tvNickname.text = currentPost.author.name
        holder.binding.tvAuthor.text = currentPost.author.name
        val avaUrl = currentPost.author.avatar
        Picasso.get().load(avaUrl).into(holder.binding.ivAvatar)

        holder.binding.tvContent.text = currentPost.content
        holder.binding.tvNumLiked.text = currentPost.totalLike.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<PostData>) {
        listPost = newList
        notifyDataSetChanged() // Cập nhật RecyclerView
    }
}