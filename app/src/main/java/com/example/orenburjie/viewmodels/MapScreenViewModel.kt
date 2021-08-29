package com.example.orenburjie.viewmodels

import com.example.orenburjie.objects.Item
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.example.orenburjie.viewmodels.base.ScreenLogicViewModel

class MapScreenViewModel: ScreenLogicViewModel() {

    private lateinit var item: Item

    init {
        Repository.getInstance().getItem()?.let {
            item = it
        } ?: back()
    }

    override fun back(){
        Router.getInstance().backToCultureFragment()
    }

    fun toCultureDescriptionFragment(){
        Router.getInstance().showCultureDescriptionFragment()
    }

    fun getLocation() = item.toPoint()

    fun getTitle() = item.title

}