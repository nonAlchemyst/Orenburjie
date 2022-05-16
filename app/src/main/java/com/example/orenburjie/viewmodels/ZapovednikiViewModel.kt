package com.example.orenburjie.viewmodels

import com.example.orenburjie.MainActivity
import com.example.orenburjie.Router
import com.example.orenburjie.objects.Item
import com.example.orenburjie.viewmodels.base.BaseViewModel

class ZapovednikiViewModel: BaseViewModel() {

    init {
        ref = MainActivity.instance?.firebase?.getReference("Priroda")?.child("Zapovedniki")
    }

    fun showNature(){
        Router.getInstance().showNatureFragment()
    }

    fun goToDescriptionFragment(item: Item){
        Router.getInstance().showDescriptionFragment(item)
    }

    fun back(){
        Router.getInstance().backToMainScreen()
    }

}