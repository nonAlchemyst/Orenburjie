package com.example.orenburjie.objects

data class Item1(
    val id: Int,
    val description: ItemDescription?,
    val map: ItemMap?,
    val restingPlaces: ItemRestingPlaces?
)
data class ItemDescription(
    val title: String?,
    val description: String?,
    val images: ArrayList<String>?
)
data class ItemMap(
    val latitude: String?,
    val longitude: String?
)
data class ItemRestingPlaces(
    val restingPlaces: ArrayList<RestingPlace>?
)