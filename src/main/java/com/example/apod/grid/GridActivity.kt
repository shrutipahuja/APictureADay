package com.example.apod.grid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apod.R
import com.example.apod.databinding.ActivityGridBinding
import com.example.apod.detail.DetailActivity

class GridActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridBinding
    private val viewModel: GridViewModel by lazy {
        ViewModelProvider(this).get(GridViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        viewModel.image.observe(this, Observer { listOfImages ->
            binding.photosGrid.adapter =
                GridAdapter(listOfImages, GridAdapter.ImageClickListener { image ->
                    viewModel.displayImageDetails(image)
                })
        })
        viewModel.navigateToSelectedImage.observe(this, Observer {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("imagedata", it)
            startActivity(intent)
        })
    }
}