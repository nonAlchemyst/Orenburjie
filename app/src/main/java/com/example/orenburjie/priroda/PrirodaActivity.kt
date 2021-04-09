package com.example.orenburjie.priroda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.orenburjie.R
import com.example.orenburjie.priroda.interfaces.OnTransferReference
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PrirodaActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, OnTransferReference {

    private var bottomNav: BottomNavigationView? = null
    private lateinit var navController: NavController
    private var ref: DatabaseReference? = FirebaseDatabase.getInstance().getReference("Priroda")

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_priroda)
        navController = Navigation.findNavController(this, R.id.prirodaFragment)
        bottomNav = findViewById(R.id.prirodaNavigation)
        bottomNav?.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == bottomNav?.selectedItemId)
            return false
        navController.navigate(item.itemId)
        return true
    }

    override fun onBackPressed() {
        finish()
    }

    override fun getReference(fragment: Int): DatabaseReference? {
        when(fragment){
            R.id.listFragment -> return ref?.child("Interesnie Mesta")
            R.id.zapovednikiListFragment -> return ref?.child("Zapovedniki")
        }
        return null
    }
}