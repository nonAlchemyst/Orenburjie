package com.example.orenburjie.viewmodels

import com.example.orenburjie.objects.Item
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.example.orenburjie.viewmodels.base.ScreenLogicViewModel

class RestingPlacesViewModel: ScreenLogicViewModel() {

    private lateinit var item: Item

    init {
        Repository.getInstance().getItem()?.let {
            item = it
        } ?: back()
    }

    fun toDescriptionFragment(){
        Router.getInstance().showDescriptionFragment()
    }

    fun toExcursionsDescriptionFragment(){
        Router.getInstance().showExcursionsDescriptionFragment()
    }

    override fun back(){
        Router.getInstance().backToMenuList()
    }

    fun getTitle() = item.title

    fun getRestingPlaces() = item.toRestingPlaces()

}