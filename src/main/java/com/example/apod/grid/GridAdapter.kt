package com.example.apod.grid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.apod.R
import com.example.apod.databinding.ImageListItemBinding
import com.example.apod.model.Image

class GridAdapter(
    private val imageList: List<Image>,
    private val imageClickListener: ImageClickListener
) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    var loadingComplete = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = ImageListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false).root
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val image = imageList[position]
        val options: RequestOptions = RequestOptions().centerCrop().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
        holder.imageListItemBinding.apply {
            apodImage.apply {
                Glide.with(context).load(image.url)
                    .apply(options).into(this)
                loadingComplete = true
            }
        }
        holder.imageListItemBinding.apodImage.setOnClickListener {
            if (loadingComplete) {
                imageClickListener.onClick(image)
            }
        }
    }

    inner class GridViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageListItemBinding: ImageListItemBinding = ImageListItemBinding.bind(itemView)
    }

    class ImageClickListener(val clickListener: (image: Image) -> Unit) {
        fun onClick(image: Image) = clickListener(image)
    }
}
