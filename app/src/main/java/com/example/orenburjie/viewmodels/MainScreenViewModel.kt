package com.example.orenburjie.viewmodels

import androidx.lifecycle.ViewModel
import com.example.orenburjie.Router

class MainScreenViewModel: ViewModel() {

    fun naturePressed(){
        Router.getInstance().showNatureFragment()
    }

    fun culturePressed(){
        Router.getInstance().showCultureFragment()
    }

    fun excursionPressed(){
        Router.getInstance().showExcursionsFragment()
    }

    fun developerPressed(){}


}