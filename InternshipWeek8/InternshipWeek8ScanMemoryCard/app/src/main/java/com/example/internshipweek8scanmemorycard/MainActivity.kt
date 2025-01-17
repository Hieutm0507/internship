package com.example.internshipweek8scanmemorycard

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.internshipweek8scanmemorycard.databinding.ActivityMainBinding
import com.example.internshipweek8scanmemorycard.ui.MediaFile
import com.example.internshipweek8scanmemorycard.ui.MediaReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainActivity(private val mediaReader: MediaReader) : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var scanFileJob: Job
    private lateinit var files: MutableList<MediaFile>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val permissions = if(Build.VERSION.SDK_INT >=33) {
            arrayOf(
                Manifest.permission.READ_MEDIA_AUDIO,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_IMAGES
            )
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        ActivityCompat.requestPermissions(this@MainActivity, permissions, 0)

        scanFileFromSD()
    }

    private fun scanFileFromSD() {
        scanFileJob = lifecycleScope.launch(Dispatchers.IO) {
            files = mediaReader.getAllMediaFiles().toMutableList()
        }
    }
}