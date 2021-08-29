package com.example.orenburjie.fragments

import android.os.Bundle
import android.view.View
import com.example.orenburjie.R
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.adapters.RestingPlacesListAdapter
import com.example.orenburjie.viewmodels.RestingPlacesViewModel
import kotlinx.android.synthetic.main.fragment_resting_places.*
import kotlinx.android.synthetic.main.layout_nav_bar_with_title.*

class RestingPlacesFragment: BaseFragment<RestingPlacesViewModel>(RestingPlacesViewModel::class.java, R.layout.fragment_resting_places) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nav_bar_wirh_title_title.text = viewModel.getTitle()
        nav_bar_with_title_back.setOnClickListener {
            viewModel.back()
        }
        resting_places_page1.setOnClickListener {
            viewModel.toDescriptionFragment()
        }
        viewModel.getRestingPlaces()?.let {
            resting_places_list.adapter = RestingPlacesListAdapter(R.layout.rp_list_item, it)
        } ?: callEmptyListDialog()
    }

    override fun onBack() {
        viewModel.back()
    }

    private fun callEmptyListDialog(){}
}