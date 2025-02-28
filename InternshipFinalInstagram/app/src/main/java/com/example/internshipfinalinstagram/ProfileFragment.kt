package com.example.internshipfinalinstagram

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.internshipfinalinstagram.adapters.GridPostAdapter
import com.example.internshipfinalinstagram.databinding.FragmentProfileBinding
import com.example.internshipfinalinstagram.viewmodels.PostViewModel
import com.example.internshipfinalinstagram.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private val userViewModel : UserViewModel by viewModel()
    private val postViewModel: PostViewModel by viewModel()
    private lateinit var gridPostAdapter : GridPostAdapter
    private val sort : String = "moi-nhat"
    private var page : Int = 1
    private val perPage : Int = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initMain()

        return binding.root
    }

    private fun initMain() {
        setObserver()
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        gridPostAdapter = GridPostAdapter(emptyList())      // Khởi tạo adapter với danh sách rỗng ban đầu
        binding.rvGridPosts.layoutManager = GridLayoutManager(context, 3)
        binding.rvGridPosts.adapter = gridPostAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun setObserver() {
        // Lấy data từ API
        userViewModel.getUserIn4(LoginActivity.currentUser)

        userViewModel.in4LiveData.observe(viewLifecycleOwner) { getIn4State ->
            if (getIn4State.isLoading) {
                Log.d("TAG_LOADING", "Đang tải dữ liệu...")
            } else if (getIn4State.result != null) {
                val userIn4 = getIn4State.result
                binding.tvUsername.text = userIn4.data.username
                binding.tvName.text = userIn4.data.name
                binding.tvBio.text = userIn4.data.introduce

                val avaUrl = userIn4.data.avatar
                if (!avaUrl.contentEquals(null)) {
                    Glide.with(binding.ivAvatar.context).load(avaUrl).centerCrop().into(binding.ivAvatar)
                }
                else {
                    binding.ivAvatar.setImageResource(R.drawable.img_default_ava)
                }

            } else if (getIn4State.error != null) {
                Log.e("TAG_ERROR", "Lỗi lấy thông tin user: ${getIn4State.error}")
            }
        }

        postViewModel.getPostsOfUser(LoginActivity.currentUser, sort, page, perPage)

        postViewModel.postsLiveData.observe(viewLifecycleOwner) { postDataState ->
            if (postDataState.isLoading) {
                Log.d("TAG_LOADING", "Đang tải dữ liệu...")
            } else if (postDataState.result != null) {
                val newPosts = postDataState.result
                val currentPosts = gridPostAdapter.getCurrentData()  // Lấy dữ liệu hiện tại từ adapter
                gridPostAdapter.updateData(currentPosts + newPosts)  // Thêm dữ liệu mới vào danh sách
                binding.tvNumPost.text = gridPostAdapter.listPost.size.toString()
                Log.d("TAG_POSTS", "Thêm ${newPosts.size} bài viết, tổng: ${currentPosts.size + newPosts.size}")
            } else if (postDataState.error != null) {
                Log.e("TAG_ERROR", "Lỗi lấy bài viết: ${postDataState.error}")
            }
        }
    }
}