package com.example.orenburjie.viewmodels

import com.example.orenburjie.objects.Item
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.example.orenburjie.viewmodels.base.BaseViewModel

class DescriptionScreenViewModel() : BaseViewModel() {

    private lateinit var item: Item

    init {
//        Repository.getInstance().getItem()?.let {
//            item = it
//        } ?: back()
    }

    fun setItem(item: Item){
        this.item = item
    }

    fun back(){
        Router.getInstance().backToMenuList()
    }

    fun toRestingPlacesFargment(item: Item){
        Router.getInstance().showRestingPlacesFragment(item)
    }

    fun toInfoFragment(item: Item){
        Router.getInstance().showExcursionsInfoFragment(item)
    }

    fun toMapFragment(item: Item){
        Router.getInstance().showMapScreenFragment(item)
    }

    fun getTitle() = item.title

    fun getDescription() = item.description

    fun getImages() = item.images

}