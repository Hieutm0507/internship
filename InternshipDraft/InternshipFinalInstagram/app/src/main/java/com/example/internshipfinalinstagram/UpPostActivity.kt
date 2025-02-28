package com.example.internshipfinalinstagram

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.internshipfinalinstagram.adapters.PreviewImgAdapter
import com.example.internshipfinalinstagram.databinding.ActivityUpPostBinding
import com.example.internshipfinalinstagram.viewmodels.PostViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UpPostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpPostBinding
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private lateinit var previewImgAdapter: PreviewImgAdapter
    private val postViewModel : PostViewModel by viewModel()
    private val imageUris : MutableList<Uri> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpPostBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        eventListeners()
    }

    private fun eventListeners() {
        binding.btCancel.setOnClickListener {
            finish()
        }

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                handleSelectedImages(result.data)
            }
        }

        binding.ivSelectedImg.setOnClickListener {
            openGallery()
        }

        binding.btPost.setOnClickListener {
            val content = binding.etCaption.text.toString()
            val imageFiles = convertUrisToFiles(this, imageUris)

            if (imageUris.isNotEmpty()) {
                setObserver(imageFiles, content)
            }
            else {
                Toast.makeText(this, "Hãy chọn ảnh nè!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setObserver(imageFiles : List<File>, content : String) {
        postViewModel.addPost(LoginActivity.currentUser, imageFiles, content)

        postViewModel.postsLiveData.observe(this) { state ->
            when {
                state.isLoading -> binding.clLoading.visibility = View.VISIBLE
                state.result != null -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun uploadSingleUri(uri: Uri) {
        val context = this // or requireContext() if in Fragment

        // Chuyển đổi Uri thành MultipartBody.Part
        val imagePart = uriToMultipartBodyPart(uri, context, "images")

        // Tạo RequestBody cho userId và content (giả sử bạn có userId và content)
        val userIdRequestBody = RequestBody.create(MediaType.parse("text/plain"), "your_user_id")
        val contentRequestBody = RequestBody.create(MediaType.parse("text/plain"), "your_content")
    }

    fun uriToMultipartBodyPart(uri: Uri, context: Context, partName: String): MultipartBody.Part {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri)
        val byteArray = inputStream?.readBytes() ?: ByteArray(0)
        val requestFile = RequestBody.create(
            MediaType.parse(contentResolver.getType(uri) ?: "image/jpeg"),
            byteArray
        )
        return MultipartBody.Part.createFormData(partName, "image.jpg", requestFile)
    }


    private fun convertUrisToFiles(context: Context, uris: MutableList<Uri>): List<File> {
        val files = mutableListOf<File>()
        for (uri in uris) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val file = File.createTempFile("temp_image", ".jpg", context.cacheDir)

                inputStream?.use { input ->
                    file.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                files.add(file)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return files
    }


    private fun handleSelectedImages(data: Intent?) {
        imageUris.clear() // Xóa danh sách cũ

        data?.let {
            if (it.clipData != null) { // Nếu chọn nhiều ảnh
                val count = it.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri = it.clipData!!.getItemAt(i).uri
                    imageUris.add(imageUri)
                }
            } else if (it.data != null) { // Nếu chỉ chọn 1 ảnh
                imageUris.add(it.data!!)
            }
        }
        Log.d("TAG_URI", imageUris.toString())

        if (imageUris.isNotEmpty()) {
            binding.rvPreviewSelectedImgs.visibility = View.VISIBLE
            binding.ivSelectedImg.visibility = View.INVISIBLE
        }

        // Cập nhật Adapter khi có ảnh mới
        previewImgAdapter = PreviewImgAdapter(imageUris)
        binding.rvPreviewSelectedImgs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPreviewSelectedImgs.adapter = previewImgAdapter
    }


    @SuppressLint("IntentReset")
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // Cho phép chọn nhiều ảnh
        imagePickerLauncher.launch(intent)
    }
}