package com.example.orenburjie.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.orenburjie.R
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.adapters.ImagesAdapter
import com.example.orenburjie.viewmodels.DescriptionScreenViewModel
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.layout_nav_bar_with_title.*

open class DescriptionScreenFragment: BaseFragment<DescriptionScreenViewModel>(DescriptionScreenViewModel::class.java, R.layout.fragment_description) {

    private var adapter: ImagesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nav_bar_wirh_title_title.text = viewModel.getTitle()
        description_info.text = viewModel.getDescription()
        nav_bar_with_title_back.setOnClickListener {
            viewModel.back()
        }
        /*description_page_2.setOnClickListener {
            viewModel.toRestingPlacesFragment()
        }*/
        viewModel.navTab?.selectLeft()
        viewModel.getImages()?.let {
            adapter = ImagesAdapter(it)
        }
        val manager = LinearLayoutManager(requireContext())
        manager.orientation = LinearLayoutManager.HORIZONTAL
        description_images.layoutManager = manager
        description_images.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(description_images)
    }

    override fun onBack() {
        viewModel.back()
    }
}