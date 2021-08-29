package com.example.orenburjie.viewmodels

import com.example.orenburjie.objects.Item
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.example.orenburjie.viewmodels.base.BaseViewModel

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