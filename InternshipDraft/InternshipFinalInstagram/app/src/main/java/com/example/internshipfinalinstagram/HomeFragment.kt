package com.example.internshipfinalinstagram

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshipfinalinstagram.adapters.PostAdapter
import com.example.internshipfinalinstagram.databinding.FragmentHomeBinding
import com.example.internshipfinalinstagram.viewmodels.PostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val postViewModel: PostViewModel by viewModel()
    private lateinit var postAdapter : PostAdapter
    private val sort : String = "moi-nhat"
    private var page : Int = 1
    private val perPage : Int = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root

        val psa = postViewModel.getAllPosts(sort, page, perPage)
        Log.d("TAG_POSTS", psa.toString())

//        postAdapter = PostAdapter(psa)
        binding.rvStories.layoutManager = LinearLayoutManager(context)
        binding.rvStories.adapter = postAdapter
    }
}