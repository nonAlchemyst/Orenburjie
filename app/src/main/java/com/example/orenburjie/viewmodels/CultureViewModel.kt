package com.example.orenburjie.viewmodels

import com.example.orenburjie.*
import com.example.orenburjie.objects.Item
import com.example.orenburjie.viewmodels.base.BaseViewModel

class CultureViewModel: BaseViewModel() {

    init {
        super.ref = MainActivity.instance?.firebase?.getReference("Culture")
    }

    fun goToDescriptionFragment(item: Item){
        Router.getInstance().showCultureDescriptionFragment(item)
    }

    fun back(){
        Router.getInstance().backToMainScreen()
    }

}