package com.example.orenburjie.viewmodels

import androidx.lifecycle.ViewModel
import com.example.orenburjie.Item
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.example.orenburjie.viewmodels.base.BaseViewModel
import com.example.orenburjie.viewmodels.base.ScreenLogicViewModel

class DescriptionScreenViewModel() : BaseViewModel() {

    private lateinit var item: Item

    init {
        Repository.getInstance().getItem()?.let {
            item = it
        } ?: back()
    }

    fun back(){
        Router.getInstance().backToMenuList()
    }

    fun toRestingPlacesFargment(){
        Router.getInstance().showRestingPlacesFragment()
    }

    fun toInfoFragment(){
        Router.getInstance().showExcursionsInfoFragment()
    }

    fun toMapFragment(){
        Router.getInstance().showMapScreenFragment()
    }

    fun getTitle() = item.title

    fun getDescription() = item.description

    fun getImages() = item.images

}