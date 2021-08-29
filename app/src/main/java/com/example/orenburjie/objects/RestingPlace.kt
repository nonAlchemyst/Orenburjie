package com.example.orenburjie.objects

import java.io.Serializable

class RestingPlace: Serializable {
    var name: String? = null
    var street: String? = null
    var phone: String? = null
    var pathToSite: String? = null

    /*init {
        this.name = name
        this.street = street
        this.phone = phone
        this.pathToSite = pathToSite
    }*/

    constructor(name: String, street: String, phone: String, pathToSite: String){
        this.name = name
        this.street = street
        this.phone = phone
        this.pathToSite = pathToSite
    }

    constructor(){}
}