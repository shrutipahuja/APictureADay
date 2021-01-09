package com.example.apod.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.apod.R
import com.example.apod.databinding.ActivityDetailBinding
import com.example.apod.model.Image

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }

    private fun initView() {
        val options: RequestOptions = RequestOptions().centerCrop().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
        val image: Image? = intent.getParcelableExtra<Image>("imagedata")
        if (image != null) {
            binding.apodImage.apply {
                Glide.with(context).load(image.url)
                    .apply(options).into(this)
            }
            binding.dateText.text = image.date
            binding.titleText.text = image.title
            binding.descText.text = image.explanation
            if (!image.copyright.isNullOrEmpty()) {
                binding.copyrightText.text = image.copyright
            }
        }
    }
}