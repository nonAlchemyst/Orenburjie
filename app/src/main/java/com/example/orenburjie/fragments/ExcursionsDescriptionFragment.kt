package com.example.orenburjie.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.orenburjie.R
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.adapters.ImagesAdapter
import com.example.orenburjie.viewmodels.DescriptionScreenViewModel
import kotlinx.android.synthetic.main.fragment_excursions_description.*
import kotlinx.android.synthetic.main.layout_nav_bar_with_title.*

class ExcursionsDescriptionFragment: BaseFragment<DescriptionScreenViewModel>(DescriptionScreenViewModel::class.java, R.layout.fragment_excursions_description) {

    private var adapter: ImagesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nav_bar_wirh_title_title.text = viewModel.getTitle()
        excursions_description_info.text = viewModel.getDescription()
        nav_bar_with_title_back.setOnClickListener {
            viewModel.back()
        }
        excursions_description_page_2.setOnClickListener {
            viewModel.toInfoFragment()
        }
        viewModel.getImages()?.let {
            adapter = ImagesAdapter(it)
        }
        val manager = LinearLayoutManager(requireContext())
        manager.orientation = LinearLayoutManager.HORIZONTAL
        excursions_description_images.layoutManager = manager
        excursions_description_images.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(excursions_description_images)
    }

    override fun onBack() {
        viewModel.back()
    }
}