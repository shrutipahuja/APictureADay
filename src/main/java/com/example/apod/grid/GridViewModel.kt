package com.example.apod.grid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apod.model.Image
import com.example.apod.utility.DataUtils

class GridViewModel : ViewModel() {

    private lateinit var imageData: String
    var imageList : List<Image>? = emptyList()

    private val _images = MutableLiveData<List<Image>>()
    val image: LiveData<List<Image>>
        get() = _images

    private val _navigateToSelectedImage = MutableLiveData<Image>()
    val navigateToSelectedImage: LiveData<Image>
        get() = _navigateToSelectedImage

    init {
        getNASAImages()
    }

    private fun getNASAImages() {
        imageData = DataUtils.readJson("data.json")
        imageList = DataUtils.fromJson(imageData)
        _images.value = imageList?.reversed()
    }

    fun displayImageDetails(image: Image) {
        _navigateToSelectedImage.value = image
    }
}