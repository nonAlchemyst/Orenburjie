package com.example.orenburjie.viewmodels

import com.example.orenburjie.Router

class NavBar {

    fun back(){
        Router.getInstance().back()
    }

}