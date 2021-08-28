package com.example.orenburjie.viewmodels

import com.example.orenburjie.MainActivity
import com.example.orenburjie.Router
import com.example.orenburjie.viewmodels.base.BaseViewModel

class ZapovednikiViewModel: BaseViewModel() {

    init {
        ref = MainActivity.instance?.firebase?.getReference("Priroda")?.child("Zapovedniki")
    }

    fun showNature(){
        Router.getInstance().showNatureFragment()
    }

    fun back(){
        Router.getInstance().backToMainScreen()
    }

}