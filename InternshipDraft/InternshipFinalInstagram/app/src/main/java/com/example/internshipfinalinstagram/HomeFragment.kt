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

        initMain()

        return binding.root
    }

    private fun initMain() {
        setObserver()
        initView()
    }

    private fun initView() {
        postAdapter = PostAdapter(emptyList()) // Khởi tạo adapter với danh sách rỗng ban đầu
        binding.rvStories.layoutManager = LinearLayoutManager(context)
        binding.rvStories.adapter = postAdapter
    }

    private fun setObserver() {
        postViewModel.getAllPosts(sort, page, perPage)

        postViewModel.postsLiveData.observe(viewLifecycleOwner) { postDataState ->
            if (postDataState.isLoading) {
                Log.d("TAG_LOADING", "Đang tải dữ liệu...")
            } else if (postDataState.result != null) {
                postAdapter.updateData(postDataState.result)
                Log.d("TAG_POSTS", postDataState.result.toString())
            } else if (postDataState.error != null) {
                Log.e("TAG_ERROR", "Lỗi lấy bài viết: ${postDataState.error}")
            }
        }
    }

}