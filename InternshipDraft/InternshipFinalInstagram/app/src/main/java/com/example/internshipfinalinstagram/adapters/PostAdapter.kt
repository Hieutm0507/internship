package com.example.internshipfinalinstagram.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internshipfinalinstagram.R
import com.example.internshipfinalinstagram.databinding.ItemPostBinding
import com.example.internshipfinalinstagram.models.PostData
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class PostAdapter(private var listPost: List<PostData> = listOf()) : RecyclerView.Adapter<PostAdapter.PostHolder>() {
    private lateinit var imageSliderAdapter: ImageSliderAdapter

    inner class PostHolder(val binding : ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int = listPost.size

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val currentPost = listPost[position]

        holder.binding.tvNickname.text = currentPost.author.name
        holder.binding.tvAuthor.text = currentPost.author.name

        // TODO: (1) Set image for avatar
        val avaUrl = currentPost.author.avatar
        if (!avaUrl.contentEquals(null)) {
            Glide.with(holder.binding.ivAvatar.context).load(avaUrl).centerCrop().into(holder.binding.ivAvatar)
        }
        else {
            holder.binding.ivAvatar.setImageResource(R.drawable.img_default_ava)
        }

        // TODO: (2) Set image for post
        val imgUrlList = currentPost.images
        imageSliderAdapter = ImageSliderAdapter(imgUrlList)
        holder.binding.vpImage.adapter = imageSliderAdapter
//        Log.d("TAG_POSTS_IMG", imgUrl.toString())
//        Glide.with(holder.itemView).load(imgUrl).into(holder.binding.ivImage)

        // TODO: (3) Like Post

        // Set text for list liked
        val personLike = currentPost.listLike
        when (personLike.size ) {
            0 -> holder.binding.tvListLike.visibility = View.GONE
            1 -> holder.binding.tvListLike.text = "Liked by ${personLike.first().name}"
            else -> holder.binding.tvListLike.text = "Liked by ${personLike.first().name} and others"
        }

        holder.binding.tvContent.text = currentPost.content
        holder.binding.tvNumLiked.text = currentPost.totalLike.toString()
        holder.binding.tvDate.text = calculateTimeAgo(currentPost.updatedAt)
    }

    fun setAvaImage() {}

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<PostData>) {
        listPost = newList
        notifyDataSetChanged() // Cập nhật RecyclerView
    }

    fun getCurrentData(): List<PostData> {
        return listPost
    }


    // Format time
    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateTimeAgo(postTime: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val postDateTime = LocalDateTime.parse(postTime, formatter)
        val currentDateTime = LocalDateTime.now(ZoneId.of("UTC"))

        val minutes = ChronoUnit.MINUTES.between(postDateTime, currentDateTime)
        val hours = ChronoUnit.HOURS.between(postDateTime, currentDateTime)
        val days = ChronoUnit.DAYS.between(postDateTime, currentDateTime)

        return when {
            minutes < 1 -> "Just now"
            minutes < 60 -> "$minutes minutes ago"
            hours < 24 -> "$hours hours ago"
            days < 7 -> "$days days ago"
            else -> postDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }
    }
}