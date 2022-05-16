package com.example.orenburjie.viewmodels

import com.example.orenburjie.objects.Item
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.example.orenburjie.viewmodels.base.ScreenLogicViewModel

class RestingPlacesViewModel: ScreenLogicViewModel() {

    private lateinit var item: Item

    fun setItem(item: Item){
        this.item = item
    }

    fun toDescriptionFragment(item: Item){
        Router.getInstance().showDescriptionFragment(item)
    }

    fun toExcursionsDescriptionFragment(item: Item){
        Router.getInstance().showExcursionsDescriptionFragment(item)
    }

    override fun back(){
        Router.getInstance().backToMenuList()
    }

    fun getTitle() = item.title

    fun getRestingPlaces() = item.toRestingPlaces()

}