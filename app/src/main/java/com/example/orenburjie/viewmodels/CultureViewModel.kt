package com.example.orenburjie.viewmodels

import com.example.orenburjie.*
import com.example.orenburjie.viewmodels.base.BaseViewModel

class CultureViewModel: BaseViewModel() {

    init {
        super.ref = MainActivity.instance?.firebase?.getReference("Culture")
    }

    override fun next() {
        Router.getInstance().showCultureDescriptionFragment()
    }

    fun back(){
        Router.getInstance().backToMainScreen()
    }

}