package com.example.orenburjie.viewmodels

import com.example.orenburjie.MainActivity
import com.example.orenburjie.Router
import com.example.orenburjie.objects.Item
import com.example.orenburjie.viewmodels.base.BaseViewModel

class NatureViewModel: BaseViewModel() {

    init {
        ref = MainActivity.instance?.firebase?.getReference("Priroda")?.child("Interesnie Mesta")
    }

    fun showZapovedniki(){
        Router.getInstance().showZapovednikiFragment()
    }

    fun goToDescriptionFragment(item: Item){
        Router.getInstance().showDescriptionFragment(item)
    }

    fun back(){
        Router.getInstance().backToMainScreen()
    }

}