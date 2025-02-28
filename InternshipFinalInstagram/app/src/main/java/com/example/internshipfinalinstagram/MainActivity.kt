package com.example.internshipfinalinstagram

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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




    }

//    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (permissions[Manifest.permission.READ_MEDIA_IMAGES] == true ||
//                permissions[Manifest.permission.READ_MEDIA_VIDEO] == true) {
//                // perform task`
//            }
//        } else {
//            if (permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true &&
//                permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true) {
//                // perform task`
//            } else {
//                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun checkPermissionAndLoad() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            requestPermissionLauncher.launch(arrayOf(
//                Manifest.permission.READ_MEDIA_IMAGES,
//                Manifest.permission.READ_MEDIA_VIDEO
//            ))
//        } else {
//            requestPermissionLauncher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
//        }
//    }


    private fun setupNavigationBar() {
        binding.bnvNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> loadFragment(HomeFragment())
                R.id.search -> loadFragment(SearchFragment())
                R.id.new_post -> {
                    val intent = Intent(this@MainActivity, SelectImageActivity::class.java)
                    startActivity(intent)
                }
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