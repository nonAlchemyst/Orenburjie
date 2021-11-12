package com.example.orenburjie.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.orenburjie.Repository

@BindingAdapter("imagePathResource")
fun loadImageFromPath(imageView: ImageView, path: String){
    Repository.loadImage(path, imageView, imageView.context)
}