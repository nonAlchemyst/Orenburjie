package com.example.orenburjie

import android.content.Context
import android.widget.ImageView
import com.example.orenburjie.ImageStorageManager.getImageFromSharedPreference
import com.example.orenburjie.ImageStorageManager.imageIsLoadedToSharedPreference
import com.example.orenburjie.ImageStorageManager.loadToImageFromFirebase

class Repository {

    /*
    * Типо глобального класса
    * Лень придумывать что-то более правильное да и зачем :D
    */

    companion object{
        private const val map_apikey: String = "b31628fd-7b04-425c-a4ab-994bb68fe5ea"
        private const val sharedPreferenceKey = "images"
        private const val readErrorMessage = "Read error"
        private var instance: Repository = Repository()
        private val mImages = MainActivity.instance.getPreference(sharedPreferenceKey)
        private val mEditor = mImages.edit()

        fun getInstance() = instance

        fun getMapApiKey() = map_apikey

        fun loadImage(path: String, image: ImageView, context: Context?){
            //some magic :/
            if(imageIsLoadedToSharedPreference(path)){
                val bitmap = getImageFromSharedPreference(path)
                image.setImageBitmap(bitmap)
            }else{
                loadToImageFromFirebase(path, image, context)
            }
        }

    }

    private var curItem: Item? = null

    fun setItem(item: Item){
        curItem = item
    }

    fun getItem() = curItem

}