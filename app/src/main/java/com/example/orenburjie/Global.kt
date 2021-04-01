package com.example.orenburjie

import android.util.Log
import android.widget.ImageView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

object Global {

    //var ref: DatabaseReference = null
    //lateinit var storage: FirebaseStorage
    var isInitialize: Boolean = false
    lateinit var storageRef: StorageReference
    val map_apikey: String = "6d955c19-fe7b-4424-b784-56f98d1e4c98"

    fun loadImageFromStorage(path: String, image: ImageView){
        storageRef = FirebaseStorage.getInstance().getReference(path)
        storageRef.downloadUrl.addOnSuccessListener {
            // Got the download URL for 'users/me/profile.png'
            Picasso.get().load(it).into(image)
        }.addOnFailureListener {
            // Handle any errors
            Log.d("Load image failured", it.message!!)
        }
    }

}