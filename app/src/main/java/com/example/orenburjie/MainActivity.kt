package com.example.orenburjie

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.orenburjie.viewmodels.MainViewModel
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    companion object{

        var instance: MainActivity? = null
            private set

    }

    private var viewModel: MainViewModel? = null

    var firebase = FirebaseDatabase.getInstance()
        private set

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
        instance = null
        super.onDestroy()
    }

    fun back(){
        //super.onBackPressed()
        finish()
    }

    fun getPreference(key: String): SharedPreferences = getSharedPreferences(key, MODE_PRIVATE)


}