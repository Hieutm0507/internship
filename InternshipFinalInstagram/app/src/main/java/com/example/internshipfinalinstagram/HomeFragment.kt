package com.example.internshipfinalinstagram

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipfinalinstagram.adapters.PostAdapter
import com.example.internshipfinalinstagram.databinding.FragmentHomeBinding
import com.example.internshipfinalinstagram.models.PostData
import com.example.internshipfinalinstagram.viewmodels.PostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val postViewModel: PostViewModel by viewModel()
    private lateinit var postAdapter : PostAdapter
    private val sort : String = "moi-nhat"
    private var page : Int = 1
    private val perPage : Int = 10

    @SuppressLint("CommitTransaction")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initMain()

        binding.rvStories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem == totalItemCount - 1) {  // Nếu cuộn đến phần tử cuối cùng
                    loadMorePosts()
                    Log.d("TAG_PAGE", page.toString())
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // For Message Fragment
        binding.ivMessage.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.add(R.id.fl_sub_fragment, MessageFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun initMain() {
        setObserver()
        initView()
    }

    private fun initView() {
        postAdapter = PostAdapter(emptyList())      // Khởi tạo adapter với danh sách rỗng ban đầu
        binding.rvStories.layoutManager = LinearLayoutManager(context)
        binding.rvStories.adapter = postAdapter
    }

    private fun setObserver() {
        // Lấy data từ API
        postViewModel.getAllPosts(sort, page, perPage)

        postViewModel.postsLiveData.observe(viewLifecycleOwner) { postDataState ->
            if (postDataState.isLoading) {
                Log.d("TAG_LOADING", "Đang tải dữ liệu...")
            } else if (postDataState.result != null) {
                val newPosts = postDataState.result
                val currentPosts = postAdapter.getCurrentData()  // Lấy dữ liệu hiện tại từ adapter
                postAdapter.updateData(currentPosts + newPosts)  // Thêm dữ liệu mới vào danh sách
                Log.d("TAG_POSTS", "Thêm ${newPosts.size} bài viết, tổng: ${currentPosts.size + newPosts.size}")
            } else if (postDataState.error != null) {
                Log.e("TAG_ERROR", "Lỗi lấy bài viết: ${postDataState.error}")
            }
        }
    }

    private fun displayUserIn4() {
        postAdapter.setOnClickListener(object : PostAdapter.OnItemClickListener {
            override fun onItemClick(item: PostData) {
                val selectedUsername = item.author.username
            }

        })
    }

    private fun loadMorePosts() {
        page++  // Tăng page lên 1
        postViewModel.getAllPosts(sort, page, perPage)  // Gọi API lấy dữ liệu mới
    }
}