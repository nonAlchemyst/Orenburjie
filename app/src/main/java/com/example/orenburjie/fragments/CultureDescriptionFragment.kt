package com.example.orenburjie.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.orenburjie.R
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.adapters.ImagesAdapter
import com.example.orenburjie.fragments.base.FragmentWithModel
import com.example.orenburjie.viewmodels.DescriptionScreenViewModel
import kotlinx.android.synthetic.main.fragment_culture_description.*
import kotlinx.android.synthetic.main.layout_nav_bar_with_title.*

class CultureDescriptionFragment: FragmentWithModel<DescriptionScreenViewModel>(DescriptionScreenViewModel::class.java, R.layout.fragment_culture_description) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getImages()?.let {
            prepareImagesList(it)
        }
    }

    private fun prepareImagesList(data: List<String>){
        val adapter = ImagesAdapter(data)
        val manager = LinearLayoutManager(requireContext())
        manager.orientation = LinearLayoutManager.HORIZONTAL
        culture_description_images.layoutManager = manager
        culture_description_images.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(culture_description_images)
    }

    override fun onBack() {
        viewModel.back()
    }

}