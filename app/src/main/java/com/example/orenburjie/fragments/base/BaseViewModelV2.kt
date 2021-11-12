package com.example.orenburjie.fragments.base

import androidx.lifecycle.ViewModel

open class BaseViewModelV2(): ViewModel() {

    private var created = true

    init {
        if(created){
            created = false
            onCreated()
        }
    }

    open fun onCreated(){}

}