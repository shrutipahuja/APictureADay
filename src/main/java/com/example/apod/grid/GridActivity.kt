package com.example.apod.grid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apod.databinding.ActivityGridBinding
import com.example.apod.detail.DetailFragment
import com.example.apod.model.Image

/*
Launcher activity to display the grid of images
 */
class GridActivity : AppCompatActivity(), GridAdapter.ImageClickListener {

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

    /**
     * Initialises the views on the grid screen
     */
    private fun initView() {
        viewModel.image.observe(this, Observer { listOfImages ->
            binding.photosGrid.adapter =
                GridAdapter(listOfImages, this)
        })
    }

    /**
     * Overriden method on click on an image
     */
    override fun onClick(images: List<Image>, position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("IMAGE_LIST", images.toTypedArray().toCollection(ArrayList()))
        bundle.putInt("IMAGE_POSITION", position)
        val detailFragment = DetailFragment()
        detailFragment.setArguments(bundle)
        detailFragment.show(supportFragmentManager.beginTransaction(), "DETAILS")
    }
}