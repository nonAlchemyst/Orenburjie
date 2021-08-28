package com.example.orenburjie.viewmodels

import androidx.lifecycle.ViewModel
import com.example.orenburjie.MainActivity
import com.example.orenburjie.Repository
import com.example.orenburjie.Router
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yandex.mapkit.MapKitFactory
import java.lang.Exception

class MainViewModel: ViewModel() {

    init {
        init()
        Router.getInstance().showMainScreenFragment()
    }

    private fun init(){
        try{
            Firebase.database.setPersistenceEnabled(true)
            MapKitFactory.setApiKey(Repository.getMapApiKey())
            MapKitFactory.initialize(MainActivity.instance?.applicationContext)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}