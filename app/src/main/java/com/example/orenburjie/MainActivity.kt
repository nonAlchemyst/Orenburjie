package com.example.orenburjie

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.orenburjie.interfaces.OnBackPressed
import com.example.orenburjie.viewmodels.MainViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yandex.mapkit.MapKitFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    companion object{

        lateinit var instance: MainActivity
            private set

    }

    var firebase = FirebaseDatabase.getInstance()
        private set
    lateinit var viewModel: MainViewModel

    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onBackPressed() {
        Router.getInstance().systemBack()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun back(){
        //super.onBackPressed()
        finish()
    }

    fun getPreference(key: String): SharedPreferences = getSharedPreferences(key, MODE_PRIVATE)
}