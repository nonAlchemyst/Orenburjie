package com.example.orenburjie

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

object ImageStorageManager {
    private const val readErrorMessage = "Read error"
    private const val sharedPreferenceKey = "images"
    private const val saveQuality = 100
    private val mImages by lazy {
        MainActivity.instance?.getPreference(sharedPreferenceKey)
    }
    private val mEditor by lazy {
        mImages?.edit()
    }

    fun loadToImageFromFirebase(path: String, image: ImageView, context: Context?){
        val storageRef = Firebase.storage.reference.child(path)
        storageRef.downloadUrl.addOnSuccessListener { uri ->
            context?.let {
                MainActivity.instance?.applicationContext?.let { it1 ->
                    Glide.with(it1)
                        .load(uri)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .addListener(object: RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean,
                            ): Boolean {
                                return true
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean,
                            ): Boolean {
                                val bitmap = (resource as BitmapDrawable).bitmap
                                loadToSharedPreference(bitmap, path)
                                return false
                            }
                        })
                        .fitCenter()
                        .into(image)
                }
            }
        }.addOnFailureListener {
            Log.d("Load image failed", it.message!!)
        }
    }

    fun getImageFromSharedPreference(key: String): Bitmap?{
        val base64Image = readImage(key)
        return base64Image?.let { decodeToBitmap(it) }
    }

    fun imageIsLoadedToSharedPreference(key: String): Boolean{
        mImages?.apply {
            return@imageIsLoadedToSharedPreference getString(key,
                readErrorMessage) != readErrorMessage
        }
        return false
    }

    private fun loadToSharedPreference(_image: Bitmap, key: String){
        val base64Image = encodeToBase64(_image)
        writeImage(key, base64Image)
    }

    private fun encodeToBase64(_image: Bitmap): String{
        val image = _image
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, saveQuality, baos)
        val b = baos.toByteArray()
        val imageEncoded = Base64.encodeToString(b, Base64.DEFAULT)
        return imageEncoded
    }

    private fun writeImage(key: String, value: String){
        mEditor?.putString(key, value)
        mEditor?.apply()
    }

    private fun readImage(key: String): String?{
        return mImages?.getString(key, readErrorMessage)
    }

    private fun decodeToBitmap(value: String): Bitmap{
        val decodeByte = Base64.decode(value, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodeByte, Base64.DEFAULT, decodeByte.size)
    }

}