package com.example.orenburjie.fragments

import android.os.Bundle
import android.view.View
import com.example.orenburjie.R
import com.example.orenburjie.fragments.base.BaseFragment
import com.example.orenburjie.priroda.adapters.RestingPlacesListAdapter
import com.example.orenburjie.viewmodels.RestingPlacesViewModel
import kotlinx.android.synthetic.main.fragment_excursions_info.*
import kotlinx.android.synthetic.main.layout_nav_bar_with_title.*

class ExcursionsInfoFragment: BaseFragment<RestingPlacesViewModel>(RestingPlacesViewModel::class.java, R.layout.fragment_excursions_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nav_bar_wirh_title_title.text = viewModel.getTitle()
        nav_bar_with_title_back.setOnClickListener {
            viewModel.back()
        }
        excursions_info_page_1.setOnClickListener {
            viewModel.toExcursionsDescriptionFragment()
        }
        viewModel.getRestingPlaces()?.let {
            excursions_info_list.adapter = RestingPlacesListAdapter(R.layout.rp_list_item, it)
        } ?: callEmptyListDialog()
    }

    override fun onBack() {
        viewModel.back()
    }

    private fun callEmptyListDialog(){}

}