package com.example.orenburjie.viewmodels

import androidx.lifecycle.ViewModel
import com.example.orenburjie.Router
import com.example.orenburjie.objects.Item
import com.example.orenburjie.viewmodels.base.BaseViewModel
import com.google.firebase.database.FirebaseDatabase

class ExcursionViewModel: BaseViewModel() {

    init {
        ref = FirebaseDatabase.getInstance().getReference("Excursions")
    }

    fun goToExcursionsDescriptionsFragment(item: Item){
        Router.getInstance().showExcursionsDescriptionFragment(item)
    }

    fun back(){
        Router.getInstance().backToMainScreen()
    }
}

