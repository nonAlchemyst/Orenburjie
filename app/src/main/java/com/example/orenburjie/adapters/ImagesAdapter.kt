package com.example.orenburjie.adapters

import androidx.databinding.ViewDataBinding
import com.example.orenburjie.R
import com.example.orenburjie.adapters.base.RecyclerViewAdapterWithBinding

class ImagesAdapter(private val images: List<String>): RecyclerViewAdapterWithBinding<RecyclerViewAdapterWithBinding.Holder>() {

    companion object{
        private const val layoutResId = R.layout.layout_image
    }

    override fun getLayoutResId(position: Int): Int = layoutResId

    override fun getHolder(binding: ViewDataBinding): Holder = Holder(binding)

    override fun getItemByPosition(position: Int): String = images[position]

    override fun getItemCount(): Int = images.size
}