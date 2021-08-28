package com.example.orenburjie.viewmodels.base

import androidx.lifecycle.ViewModel
import com.example.orenburjie.Router

open class ScreenLogicViewModel: ViewModel() {

    open fun back(){
        Router.getInstance().back()
    }

}