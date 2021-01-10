package com.example.apod.grid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apod.model.Image
import com.example.apod.utility.DataUtils

/**
 * Viewmodel class associated with grid activity
 */
class GridViewModel : ViewModel() {

    lateinit var imageData: String
    var imageList : List<Image>? = emptyList()

    private val _images = MutableLiveData<List<Image>>()
    val image: LiveData<List<Image>>
        get() = _images

    /**
     * Called on instance of viewmodel
     */
    init {
        getNASAImages()
    }

    /**
     * Loads the list of images from json object
     */
    fun getNASAImages() {
        imageData = DataUtils.readJson("data.json")
        imageList = DataUtils.fromJson(imageData)
        _images.value = imageList?.reversed()
    }
}