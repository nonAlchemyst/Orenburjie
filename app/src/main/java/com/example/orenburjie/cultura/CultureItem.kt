package com.example.orenburjie.cultura

import com.example.orenburjie.priroda.objects.RestingPlace
import com.yandex.mapkit.geometry.Point
import java.io.Serializable

class CultureItem: Serializable {
    var id: String? = null
    var title: String? = null
    var description: String? = null
    var images: ArrayList<String>? = null
    var latitude: String? = null
    var longitude: String? = null
    var street: String? = null

    constructor(id:String, title:String, description:String, images: ArrayList<String>, latitude: String, longitude: String){
        this.id = id
        this.title = title
        this.description = description
        this.images = images
        this.latitude = latitude
        this.longitude = longitude
    }

    constructor(){}

    fun toPoint(): Point {
        return Point(latitude!!.toDouble(), longitude!!.toDouble())
    }

}