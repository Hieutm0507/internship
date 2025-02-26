package com.example.internshipfinalinstagram

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.internshipfinalinstagram.databinding.ActivityMainBinding
import com.example.internshipfinalinstagram.models.PostData
import com.example.internshipfinalinstagram.viewmodels.PostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val postViewModel : PostViewModel by viewModel()
    private val sort : String = "moi-nhat"
    private var page : Int = 1
    private val perPage : Int = 10
    private lateinit var listPost : MutableLiveData<PostData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: Splash Screen
        Handler(Looper.getMainLooper()).postDelayed({
            binding.clSplashScreen.visibility = View.GONE
            binding.bnvNavBar.visibility = View.VISIBLE
            binding.flContainer.visibility = View.VISIBLE
            setupNavigationBar()
        }, 3000)


        // TODO: Get list of posts for displaying in HomeFragment
//        val psa = postViewModel.getAllPosts(sort, page, perPage)
//        Log.d("TAG_POSTS", psa.toString())
//        postViewModel.allPosts.observe(this) { posts ->
//            Log.d("TAG_POSTS", posts.toString()) // Log dữ liệu khi nhận được từ API
//        }
//        postViewModel.getAllPosts(sort, page, perPage)
    }


    private fun setupNavigationBar() {
        binding.bnvNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> loadFragment(HomeFragment())
                R.id.search -> loadFragment(SearchFragment())
                R.id.new_post -> loadFragment(NewPostFragment())
                R.id.noti -> loadFragment(NotificationFragment())
                R.id.profile -> loadFragment(ProfileFragment())
            }
            true
        }

        // Default fragment
        binding.bnvNavBar.selectedItemId = R.id.home
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}