package com.example.orenburjie.objects

import com.yandex.mapkit.geometry.Point
import java.io.Serializable

//Super duper wooper ugly class :D
class Item: Serializable{
    var id: String? = null
    var title: String? = null
    var description: String? = null
    var images: ArrayList<String>? = null
    var latitude: String? = null
    var longitude: String? = null
    var restingPlaces: ArrayList<RestingPlace>? = null

    constructor(id:String, title:String, description:String, images: ArrayList<String>, latitude: String, longitude: String, restingPlaces: ArrayList<RestingPlace>){
        this.id = id
        this.title = title
        this.description = description
        this.images = images
        this.latitude = latitude
        this.longitude = longitude
        this.restingPlaces = restingPlaces
    }

    constructor(id:String, title:String, description:String, images: ArrayList<String>, latitude: String, longitude: String){
        this.id = id
        this.title = title
        this.description = description
        this.images = images
        this.latitude = latitude
        this.longitude = longitude
    }

    constructor(id:String, title:String, description:String, images: ArrayList<String>){
        this.id = id
        this.title = title
        this.description = description
        this.images = images
    }

    constructor(){}

    fun toPoint(): Point {
        if(latitude == null || longitude == null){
            return Point(0.0, 0.0)
        }
        return Point(latitude!!.toDouble(), longitude!!.toDouble())
    }

    fun toRestingPlaces(): ArrayList<RestingPlace>? {
        return restingPlaces
    }
}