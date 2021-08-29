package com.example.orenburjie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.orenburjie.R
import com.example.orenburjie.Repository

class ImagesAdapter(private val context: Context, private val resource: Int, private val images: List<String>): RecyclerView.Adapter<ImagesAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.images_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(resource, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val path = images[position]
        //Global.loadImageFromStorage(path, holder.image, context)
        Repository.loadImage(path, holder.image, context)
    }
}