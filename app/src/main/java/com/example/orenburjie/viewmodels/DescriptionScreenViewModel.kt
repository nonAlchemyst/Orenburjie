package com.example.orenburjie.viewmodels

import com.example.orenburjie.objects.Item
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.example.orenburjie.fragments.base.BaseViewModelV2
import com.example.orenburjie.viewmodels.base.BaseViewModel

class DescriptionScreenViewModel() : BaseViewModel() {

    private var item: Item? = null
    val navTab = NavigationTab().apply { setListener(object: NavigationTab.OnSelect(){
        override fun onRightSelected() {
            toMapFragment()
        }
    })}
    val navBar = NavBar()

    init {
        Repository.getInstance().getItem()?.let {
            item = it
        } ?: back()
    }

    override fun onCreated() {
        navTab.selectLeft()
    }

    fun back(){
        Router.getInstance().backToMenuList()
    }

    fun toRestingPlacesFragment(){
        Router.getInstance().showRestingPlacesFragment()
    }

    fun toInfoFragment(){
        Router.getInstance().showExcursionsInfoFragment()
    }

    fun toMapFragment(){
        Router.getInstance().showMapScreenFragment()
    }

    fun getTitle() = item?.title

    fun getDescription() = item?.description

    fun getImages() = item?.images

}